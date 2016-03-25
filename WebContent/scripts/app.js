Ext.example = function() {
	var msgCt;

	function createBox(t, s) {
		return '<div class="msg"><h3>' + t + '</h3><p>' + s + '</p></div>';
	}
	return {
		msg : function(title, content) {
			if (!msgCt) {
				msgCt = Ext.DomHelper.insertFirst(document.body, {
							id : 'msg-div'
						}, true);
			}
			var m = Ext.DomHelper.append(msgCt, createBox(title, content), true);
			m.hide();
			m.slideIn('t').ghost("t", {
						delay : 1000,
						remove : true
					});
		}
	};
}();	

/* 应用的遮罩层 */
var global_splashscreen;
Ext.onReady(function() {
    // Start the mask on the body and get a reference to the mask
    global_splashscreen = Ext.getBody().mask('应用加载中，请稍等...', 'splashscreen');
    // Add a new class to this mask as we want it to look different from
    // the default.
    global_splashscreen.addCls('splashscreen');
    // Insert a new div before the loading icon where we can place our
    // logo.
    Ext.DomHelper.insertFirst(Ext.query('.x-mask-msg')[0], {
        cls: 'x-splash-icon'
    });
});

/**
 * [路径设置]
 * @type {Boolean}
 */
Ext.Loader.setConfig({
	disableCaching: false,
    enabled: true,
    paths: {
    	'Ext': 'ext/src',
    	'Common': 'common',
        'Ext.ux': 'ext/src/ux',
        'Overrides': 'overrides'
    }
});

Ext.application({
	extend: 'Wenjoy.Application',
	
    requires: [
    	'Ext.container.Viewport',
    	'Common.util.Config',
    	'Common.util.Util',
        'Common.util.HttpUtil',
    	'Common.permission.Permission',
    	'Overrides.*',
    	'Overrides.grid.plugin.RowEditing',
    	'Overrides.form.field.ComboBox',
    	'Ext.ux.CropWindow',
        'Ext.ux.PagingToolbarResizer',
    	'Ext.grid.plugin.CellEditing',
    	'Ext.data.*',
    	'Ext.state.CookieProvider',
    	'Ext.chart.axis.Category',
        'Ext.toolbar.Paging',
        'Ext.ux.form.SearchField'
    ],
    
    name: 'Wenjoy',
    appFolder: 'app',
    autoCreateViewport: true,
	
    /**
     * 程序入口
     */
    launch: function() {
        // Setup a task to fadeOut the splas hscreen
        var task = new Ext.util.DelayedTask(function() {
            // Fade out the body mask
            global_splashscreen.fadeOut({
                duration: 2000,
                remove: true
            });
            // Fade out the icon and message
            global_splashscreen.next().fadeOut({
                duration: 2000,
                remove: true,
                listeners: {
                    afteranimate: function() {
                        // Set the body as unmasked after the
                        // animation
                        Ext.getBody().unmask();
                    }
                }
            });
        });
        // Run the fade 500 milliseconds after launch.
        task.delay(1000);
		
        Wenjoy.app = this;
        Wenjoy.app.lgname = app.lgname;
        Wenjoy.app.sessionid = app.sessionid;
        //设置从后台获取的权限json
        permission.server_permcollections = Ext.JSON.decode(app.server_permcollections);
		util.inIt();		//工具类初始化
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider({
            expires: new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 7))
            // 7 days from now
        }));
    }
});