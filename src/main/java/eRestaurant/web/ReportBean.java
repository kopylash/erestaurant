package eRestaurant.web;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import eRestaurant.entity.ReportByMenu;
import eRestaurant.entity.ReportTotal;
import eRestaurant.service.ReportService;

@Named
@Scope("request")
public class ReportBean {
	@Inject
	private ReportService reportService;
	private List<ReportTotal> totalReportList;
	private List<ReportByMenu> reportByMenuList;
	private Date startDate;
	private Date finishDate;

	public ReportBean() {

	}

	@PostConstruct
	public void init() {
		totalReportList = Collections.emptyList();
	}

	public List<ReportByMenu> getReportByMenuList() {
		return reportByMenuList;
	}

	public void setReportByMenuList(List<ReportByMenu> reportByMenuList) {
		this.reportByMenuList = reportByMenuList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public List<ReportTotal> getTotalReportList() {
		return totalReportList;
	}

	public void setTotalReportList(List<ReportTotal> totalReportList) {
		this.totalReportList = totalReportList;
	}

	public String getTotalReport() {
		totalReportList = reportService.getTotalReport(startDate, finishDate);
		return "report";
	}

	public double calculateTotalSum() {
		double result = 0d;

		for (ReportTotal r : totalReportList) {
			result += r.getTotalSum().doubleValue();
		}
		return result;
	}

	public String getReportByMenu() {
		reportByMenuList = reportService.getReportByMenuItems(startDate,
				finishDate);
		return "reportByMenu";
	}
}
