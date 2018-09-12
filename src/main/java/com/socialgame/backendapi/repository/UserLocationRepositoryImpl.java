package com.socialgame.backendapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.socialgame.backendapi.model.UserLocation;

public class UserLocationRepositoryImpl implements UserLocationRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<UserLocation> getClosest(String userID) {
		String sql = "SELECT prospect.* FROM user_location AS target "
				   + "LEFT JOIN user_location AS prospect "
				   + "WHERE target.user_id = ? AND target.user_id <> prospect.user_id "
				   + "ORDER BY "
				   + "	POWER((RADIANS(prospect.latitude) - RADIANS(target.latitude))/2, 2) + "
				   + "	COS(RADIANS(target.latitude))*COS(RADIANS(prospect.latitude)) * "
				   + "	POWER((RADIANS(prospect.longitude) - RADIANS(target.longitude))/2, 2) ASC "
				   + "LIMIT 20";
		Query query = entityManager.createNativeQuery(sql, UserLocation.class);
		query.setParameter(1, userID);
		return query.getResultList();
	}

}
