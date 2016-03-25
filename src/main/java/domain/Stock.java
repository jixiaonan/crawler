package domain;

/**
 * Created by jixiaonan on 16/3/2.
 */
public class Stock extends BaseDomain {
    private String code;

    private String link;

    private String netAmount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }
}
