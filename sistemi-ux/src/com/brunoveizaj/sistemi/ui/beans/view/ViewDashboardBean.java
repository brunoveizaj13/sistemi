package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.constants.IPatronageType;
import com.brunoveizaj.sistemi.ui.models.AlbaniaStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.KeyValue;
import com.brunoveizaj.sistemi.ui.models.MunicipalityDTO;
import com.brunoveizaj.sistemi.ui.models.MunicipalityStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.QvDTO;
import com.brunoveizaj.sistemi.ui.models.QvStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.RegionStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.UnitStatisticDTO;
import com.brunoveizaj.sistemi.ui.services.HelperService;
import com.brunoveizaj.sistemi.ui.services.QvStatsService;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewDashboardBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	String activeDash;
	
	Date from;
	Date to;
	
	Integer regionId;
	Integer municipId;
	Integer unitId;
	List<QvDTO> qvs;
	Integer qvId;
	
	
	AlbaniaStatisticDTO albStat;
	RegionStatisticDTO regionStat;
	MunicipalityStatisticDTO municipStat;
	UnitStatisticDTO unitStat;
	QvStatisticDTO qvStat;
	
	
	LineChartModel patronageModel;
	LineChartModel inPatronageModel;
	LineChartModel instPatronageModel;
	LineChartModel poisModel;
	LineChartModel inInstPatronageModel;
	PieChartModel institutionModel;
	
	
	boolean renderBack = false;
	
	
	@PostConstruct
	public void load()
	{
		
		init();
	}
	
	
	public void init()
	{
		try {
			this.from = new SimpleDateFormat("ddMMyyyy").parse("20012019");
			this.to = Calendar.getInstance().getTime();
		} catch (ParseException e) {
		}
		
		String munId = nav.getParam("municip_id");
    	if(StringUtil.isValid(munId))
    	{
    		this.municipId = Integer.valueOf(munId);
    		MunicipalityDTO m = new HelperService().findMunicipality(municipId);
    		this.regionId = m.getRegion().getId();
    		onMunicipalitySelect();
    		renderBack = true;
		}
		else
		{
			onRegionSelect();
		}

	}
	
	
	public void onRegionSelect()
	{
		this.municipId = null;
		this.unitId = null;
		this.qvId = null;
		
		this.albStat = null;
		this.regionStat = null;
		this.municipStat = null;
		this.unitStat = null;
		this.qvStat = null;
		
		if(this.regionId == null)
		{
			this.albStat = new QvStatsService().getAlbaniaStatistic();
			this.activeDash = "dashboard_albania.xhtml";
		}
		else
		{
			this.regionStat = new QvStatsService().getRegionStatistic(regionId);
			this.activeDash = "dashboard_region.xhtml";
		}
		
		
		generateCharts();
		
		
	}
	
	
	public void onMunicipalitySelect()
	{
		this.unitId = null;
		this.qvId = null;
		
		this.albStat = null;
		this.regionStat = null;
		this.municipStat = null;
		this.unitStat = null;
		this.qvStat = null;
		
		if(this.municipId == null)
		{
			this.regionStat = new QvStatsService().getRegionStatistic(regionId);
			this.activeDash = "dashboard_region.xhtml";
		}
		else
		{
			this.municipStat = new QvStatsService().getMunicipalityStatistic(municipId);
			this.activeDash = "dashboard_municip.xhtml";
		}
		
		
		generateCharts();
		
	}
	
	

	public void onUnitSelect()
	{
		
		this.qvId = null;
		
		this.albStat = null;
		this.regionStat = null;
		this.municipStat = null;
		this.unitStat = null;
		this.qvStat = null;
		
		if(this.unitId == null)
		{
			this.qvs = null;
			this.municipStat = new QvStatsService().getMunicipalityStatistic(municipId);
			this.activeDash = "dashboard_municip.xhtml";
		}
		else
		{
			this.qvs = new HelperService().listQv(null, null, unitId, null);
			this.unitStat = new QvStatsService().getUnitStatistic(unitId);
			this.activeDash = "dashboard_unit.xhtml";
		}
		
		
		generateCharts();
		
	}
	
	public void onQvSelect()
	{
		
		
		this.albStat = null;
		this.regionStat = null;
		this.municipStat = null;
		this.unitStat = null;
		this.qvStat = null;
		
		if(this.qvId == null)
		{
			this.unitStat = new QvStatsService().getUnitStatistic(unitId);
			this.activeDash = "dashboard_unit.xhtml";
		}
		else
		{
			this.qvStat = new QvStatsService().getQvStatistic(qvId);
			this.activeDash = "dashboard_qv.xhtml";
		}
		
		
		generateCharts();
		
	}
	
    private void generateCharts() {
		
		generatePatronageChart();
		generateInPatronageChart();
		generatePoiChart();
		generateInstPatronageChart();
		generateInInstPatronageChart();
		generateInstitutionChart();
	}
	
	public void generatePatronageChart()
	{
		this.patronageModel = new LineChartModel();
		List<KeyValue> list = new QvStatsService().getPatronagesByDate(from, to, IPatronageType.PERSON, regionId, municipId, unitId, qvId);		
		patronageModel.setShowPointLabels(true);
		LineChartSeries days = new LineChartSeries();
		days.setLabel("Patronazhistë");
		days.setShowLine(true);
		days.setShowMarker(true);
		
		if(list != null && !list.isEmpty())
		{
			for(KeyValue k : list)
			{
				days.set(k.getKey(), (Integer)k.getValue());
			}
		}
		else
		{
			days.set("ska", 0);
		}
		
		patronageModel.addSeries(days);
		patronageModel.setExtender("skinChart");
		
		CategoryAxis axis = new CategoryAxis("Datat");
        axis.setTickAngle(-50);      
 
        patronageModel.getAxes().put(AxisType.X, axis);
        patronageModel.getAxis(AxisType.Y).setMin(0);

	}
	
	public void generateInPatronageChart()
	{
		this.inPatronageModel = new LineChartModel();
		List<KeyValue> list = new QvStatsService().getInPatronagesByDate(from, to, IPatronageType.PERSON, regionId, municipId, unitId, qvId);		
		
		inPatronageModel.setShowPointLabels(true);
		LineChartSeries days = new LineChartSeries();
		days.setLabel("Nën patronazh");
		days.setShowLine(true);
		days.setShowMarker(true);
		
		if(list != null && !list.isEmpty())
		{
			for(KeyValue k : list)
			{
				days.set(k.getKey(), (Integer)k.getValue());
			}
		}
		else
		{
			days.set("ska", 0);
		}
		
		inPatronageModel.addSeries(days);
		inPatronageModel.setExtender("skinChart");
		
		CategoryAxis axis = new CategoryAxis("Datat");
        axis.setTickAngle(-50);      
 
        inPatronageModel.getAxes().put(AxisType.X, axis);
        inPatronageModel.getAxis(AxisType.Y).setMin(0);
	}
	
	public void generateInstPatronageChart()
	{
		this.instPatronageModel = new LineChartModel();
		List<KeyValue> list = new QvStatsService().getPatronagesByDate(from, to, IPatronageType.INSTITUTION, regionId, municipId, unitId, qvId);		
		
		instPatronageModel.setShowPointLabels(true);
		LineChartSeries days = new LineChartSeries();
		days.setLabel("Patronazhist institucioni");
		days.setShowLine(true);
		days.setShowMarker(true);
		
		if(list != null && !list.isEmpty())
		{
			for(KeyValue k : list)
			{
				days.set(k.getKey(), (Integer)k.getValue());
			}
		}
		else
		{
			days.set("ska", 0);
		}
		
		instPatronageModel.addSeries(days);
		instPatronageModel.setExtender("skinChart");
		
		CategoryAxis axis = new CategoryAxis("Datat");
        axis.setTickAngle(-50);      
 
        instPatronageModel.getAxes().put(AxisType.X, axis);
        instPatronageModel.getAxis(AxisType.Y).setMin(0);
	}
	
	public void generateInInstPatronageChart()
	{
		this.inInstPatronageModel = new LineChartModel();
		List<KeyValue> list = new QvStatsService().getInPatronagesByDate(from, to, IPatronageType.INSTITUTION, regionId, municipId, unitId, qvId);		
		
		inInstPatronageModel.setShowPointLabels(true);
		LineChartSeries days = new LineChartSeries();
		days.setLabel("Të patronazhuar në institucione");
		days.setShowLine(true);
		days.setShowMarker(true);
		
		if(list != null && !list.isEmpty())
		{
			for(KeyValue k : list)
			{
				days.set(k.getKey(), (Integer)k.getValue());
			}
		}
		else
		{
			days.set("ska", 0);
		}
		
		inInstPatronageModel.addSeries(days);
		inInstPatronageModel.setExtender("skinChart");
		
		CategoryAxis axis = new CategoryAxis("Datat");
        axis.setTickAngle(-50);      
 
        inInstPatronageModel.getAxes().put(AxisType.X, axis);
        inInstPatronageModel.getAxis(AxisType.Y).setMin(0);
	}

	public void generatePoiChart()
	{
		this.poisModel = new LineChartModel();
		List<KeyValue> list = new QvStatsService().getPoisByDate(from, to, regionId, municipId, unitId, qvId);		
		
		poisModel.setShowPointLabels(true);
		LineChartSeries days = new LineChartSeries();
		days.setLabel("Të patronazhuar në institucione");
		days.setShowLine(true);
		days.setShowMarker(true);
		
		if(list != null && !list.isEmpty())
		{
			for(KeyValue k : list)
			{
				days.set(k.getKey(), (Integer)k.getValue());
			}
		}
		else
		{
			days.set("ska", 0);
		}
		
		poisModel.addSeries(days);
		poisModel.setExtender("skinChart");
		
		CategoryAxis axis = new CategoryAxis("Datat");
        axis.setTickAngle(-50);      
 
        poisModel.getAxes().put(AxisType.X, axis);
        poisModel.getAxis(AxisType.Y).setMin(0);
	}
	
	public void generateInstitutionChart()
	{
		this.institutionModel = new PieChartModel();
	}
	
	
}
