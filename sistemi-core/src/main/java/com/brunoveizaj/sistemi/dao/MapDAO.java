package com.brunoveizaj.sistemi.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.models.map.BuildingMap;
import com.brunoveizaj.sistemi.models.map.PatronagePoint;
import com.brunoveizaj.sistemi.models.map.PersonPoint;
import com.brunoveizaj.sistemi.models.map.PoiPoint;
import com.brunoveizaj.sistemi.models.map.QvMap;
import com.brunoveizaj.sistemi.models.map.UnitMap;

@Repository
@SuppressWarnings("unchecked")
public class MapDAO {

	
	
	@PersistenceContext
	EntityManager em;
	
	
	
	
	
	
	public List<PersonPoint> getPersonPointByNid(String nid)
	{
		List<Object[]> result = em.createNativeQuery("select "
				+ "t.nid,"
				+ "t.full_name,"
				+ "t.voting_no,"
				+ "t.qv_code,"
				+ "t.fraction,"
				+ "t.shape_point "
				+ "FROM NEW_APP.V_PERSON t "
				+ "WHERE t.nid ='"+nid+"'")
				.getResultList();
		
		if(result != null && !result.isEmpty())
		{
			List<PersonPoint> list = new ArrayList<>();
			for(Object[] o : result)
			{
				PersonPoint p = new PersonPoint();
				
				p.setNid((String)o[0]);
				p.setFullName((String)o[1]);
				p.setVotingListNo(((BigDecimal)o[2]).intValue());
				p.setQvCode((String)o[3]);
				p.setFraction((String)o[4]);
				p.setPoint((Clob)o[5]);
				
				list.add(p);
			}
			
			return list;
		}
		
		return null;
		
		
	}
	
	public List<PatronagePoint> getPatronagesPointByArea(Integer qvId, Integer unitId, Integer patronageTypeId)
	{
		
		String FROM_TBL = "";
		String WHERE_CLAUSE = " ";
		
		if(qvId != null) 
		{
				FROM_TBL = "new_app.v_patronage_qvid";
				WHERE_CLAUSE = "QV_ID='"+qvId+"'";
		}
		if(unitId != null) 
		{
			FROM_TBL = "new_app.v_patronage_unitid";
			WHERE_CLAUSE = "UNIT_ID='"+unitId+"'";
		}
		
		WHERE_CLAUSE += " AND PATRONAGE_TYPE_ID="+patronageTypeId;
		
		String sql = "select PATRONAGE_ID, PATRONAGE_TYPE_ID, PATRONAGE_TYPE_TAG, NID, FULL_NAME, SHAPE_POINT from "+FROM_TBL+" where "+WHERE_CLAUSE;
		
		List<Object[]> result = em.createNativeQuery(sql)
				.getResultList();
		
		if(result != null && !result.isEmpty())
		{
			List<PatronagePoint> list = new ArrayList<>();
			for(Object[] o : result)
			{
				PatronagePoint p = new PatronagePoint();
				
				p.setPatronageId(((BigDecimal)o[0]).intValue());
				p.setPatronageTypeId(((BigDecimal)o[1]).intValue());
				p.setPatronageType((String)o[2]);
				p.setNid((String)o[3]);
				p.setFullName((String)o[4]);
				p.setPoint((Clob)o[5]);
				
				list.add(p);
			}
			
			return list;
		}
		
		return null;
	}
	//akoma
	public List<PersonPoint> getInPatronagePointsByPatronageNid(Integer patronageNid, Integer patronageTypeId)
	{
		
		
		String sql = "select PATRONAGE_ID, PATRONAGE_TYPE_ID, PATRONAGE_TYPE_TAG, NID, FULL_NAME, SHAPE_POINT from ";
		
		List<Object[]> result = em.createNativeQuery(sql)
				.getResultList();
		
		if(result != null && !result.isEmpty())
		{
			List<PersonPoint> list = new ArrayList<>();
			for(Object[] o : result)
			{
				PersonPoint p = new PersonPoint();
				
				p.setNid((String)o[0]);
				p.setFullName((String)o[1]);
				p.setVotingListNo(((BigInteger)o[2]).intValue());
				p.setQvCode((String)o[3]);
				p.setFraction((String)o[4]);
				p.setPoint((Clob)o[5]);
				
				list.add(p);
			}
			
			return list;
		}
		
		return null;
	}
	//akoma
	public List<PersonPoint> getInPatronagePointsByArea(Integer qvId, Integer unitId, Integer patronageTypeId)
	{
		String sql = "select PATRONAGE_ID, PATRONAGE_TYPE_ID, PATRONAGE_TYPE_TAG, NID, FULL_NAME, SHAPE_POINT from ";
		
		List<Object[]> result = em.createNativeQuery(sql)
				.getResultList();
		
		if(result != null && !result.isEmpty())
		{
			List<PersonPoint> list = new ArrayList<>();
			for(Object[] o : result)
			{
				PersonPoint p = new PersonPoint();
				
				p.setNid((String)o[0]);
				p.setFullName((String)o[1]);
				p.setVotingListNo(((BigInteger)o[2]).intValue());
				p.setQvCode((String)o[3]);
				p.setFraction((String)o[4]);
				p.setPoint((Clob)o[5]);
				
				list.add(p);
			}
			
			return list;
		}
		
		return null;
	}
	//akoma
	public List<PoiPoint> getPoisPointByArea(Integer qvId, Integer unitId)
	{
		return null;
	}
	
	public List<BuildingMap> getBuildingsByArea(Integer qvId, Integer unitId)
	{
		String sql = "select "
				+ "t.building_id,"
				+ "t.building_no,"
				+ "t.building_code,"
				+ "t.has_data,"
				+ "t.shape_center,"
				+ "t.shape "
				+ "FROM ";
		
		if(qvId != null)
		{
			sql += "V_BUILDINGS_QVID t WHERE QV_ID="+qvId;
		}
		else if(unitId != null)
		{
			sql += "V_BUILDINGS_UNITID t WHERE UNIT_ID="+unitId;
		}
		
		
		List<Object[]> result = em.createNativeQuery(sql)
				.getResultList();
		
		if(result != null && !result.isEmpty())
		{
			List<BuildingMap> list = new ArrayList<>();
			for(Object[] o : result)
			{
				BuildingMap p = new BuildingMap();
				
				p.setBuildingId(((BigDecimal)o[0]).intValue());
				p.setBuildingNo((String)o[1]);
				p.setBuildingCode(((String)o[2]));
				p.setHasData(((BigDecimal)o[3]).intValue());
				p.setCenter((Clob)o[4]);
				p.setShape((Clob)o[5]);
				
				list.add(p);
			}
			
			return list;
		}
		
		return null;
	}
	
	public QvMap getQvById(Integer qvId)
	{
		List<Object[]> result = em.createNativeQuery("select "
				+ "t.qv_id,"
				+ "t.qv_code,"
				+ "t.shape_center,"
				+ "t.shape "
				+ "FROM V_QV_UNITID t "
				+ "WHERE t.qv_id ="+qvId)
				.getResultList();
		
		if(result != null && !result.isEmpty())
		{
			Object[] o = result.get(0);
			QvMap qv = new QvMap();
			qv.setQvId(((BigDecimal)o[0]).intValue());
			qv.setQvCode((String)o[1]);					
			qv.setCenter((Clob)o[2]);
			qv.setShape((Clob)o[3]);
		
	     	return qv;

		}
		
		return null;
	}
	
	public List<QvMap> getQvsByUnitId(Integer unitId)
	{
		List<Object[]> result = em.createNativeQuery("select "
				+ "t.qv_id,"
				+ "t.qv_code,"
				+ "t.shape_center,"
				+ "t.shape "
				+ "FROM V_QV_UNITID t "
				+ "WHERE t.unit_id ="+unitId)
				.getResultList();
		
		if(result != null && !result.isEmpty())
		{
			List<QvMap> list = new ArrayList<>();
			for(Object[] o : result)
			{
				QvMap qv = new QvMap();
				qv.setQvId(((BigDecimal)o[0]).intValue());
				qv.setQvCode((String)o[1]);		
				qv.setCenter((Clob)o[2]);
				qv.setShape((Clob)o[3]);
				
				list.add(qv);
			}
	     	return list;

		}
		
		return null;
	}
	
	public UnitMap getUnitById(Integer unitId)
	{
		return null;
	}
	
	public List<PersonPoint> getFirstTimeVoterPointsByArea(Integer qvId, Integer unitId)
	{
		return null;
	}
	
	public List<PersonPoint> getMemberPointsByArea(Integer qvId, Integer unitId)
	{
		return null;
	}
	
	
	
	
}
