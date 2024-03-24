package reportManage;

import java.sql.Date;

import assetManage.Asset;

public class Report {
	private int reportID;
	private Asset asset;
	
	private String reportType;
	private Date reportDate;
	private String notes;
	private String title;
	private String status;
	
	
	
	
	
	
	public Report(int reportID, Asset asset, Date reportDate, String notes, String status, String title, String reportType) {
		this.reportID = reportID;
		this.asset = asset;
		this.reportDate = reportDate;
		this.notes = notes;
		this.status = status;
		this.title = title;
		this.reportType = reportType;
	}

	public String getReportType() {
		return reportType;
	}

	public int getReportID() {
		return reportID;
	}

	public Asset getAsset() {
		return asset;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public String getNotes() {
		return notes;
	}
	public String getStatus() {
		return status;
	}
	
	public String getTitle() {
		return title;
	}
	
	
}
