package tk.tejanikaushik.coinectus.DataTest;

public class Data {
    private int id;
    private String time;
    private String amountGet;
    private String amountUse;
    private String reason;

    public Data(int id, String time, String amountGet, String amountUse, String reason) {
        this.id = id;
        this.time = time;
        this.amountGet = amountGet;
        this.amountUse = amountUse;
        this.reason = reason;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmountGet() {
        return amountGet;
    }

    public void setAmountGet(String amountGet) {
        this.amountGet = amountGet;
    }

    public String getAmountUse() {
        return amountUse;
    }

    public void setAmountUse(String amountUse) {
        this.amountUse = amountUse;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
