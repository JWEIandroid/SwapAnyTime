package entiry;

/**
 * Created by weij on 2018/1/31.
 */

public class RecordResponse {

    //买入订单
    private Buyrecord buyrecord;

    //卖出订单
    private SaleRecord salerecord;

    //收藏订单
    private ForkRecord forkRecord;

    //发布订单
    private ReportRecord reportrecord;

    public Buyrecord getBuyrecord() {
        return buyrecord;
    }

    public RecordResponse setBuyrecord(Buyrecord buyrecord) {
        this.buyrecord = buyrecord;
        return this;
    }

    public SaleRecord getSalerecord() {
        return salerecord;
    }

    public RecordResponse setSalerecord(SaleRecord salerecord) {
        this.salerecord = salerecord;
        return this;
    }

    public ForkRecord getForkRecord() {
        return forkRecord;
    }

    public RecordResponse setForkRecord(ForkRecord forkRecord) {
        this.forkRecord = forkRecord;
        return this;
    }

    public ReportRecord getReportrecord() {
        return reportrecord;
    }

    public RecordResponse setReportrecord(ReportRecord reportrecord) {
        this.reportrecord = reportrecord;
        return this;
    }


}
