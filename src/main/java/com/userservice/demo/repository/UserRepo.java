package com.userservice.demo.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.userservice.demo.schema.generated.user_service_demo.Tables;
import com.userservice.demo.schema.generated.user_service_demo.tables.records.UserRecord;

@Repository
public class UserRepo {
	@Autowired
	private DSLContext dslContext;

	private com.userservice.demo.schema.generated.user_service_demo.tables.User table = Tables.USER;

	public int create(String phone) {
		UserRecord userRec = dslContext.newRecord(table);
		userRec.setPhone(phone);
		userRec.store();
		return userRec.getId();
	}

	public UserRecord getUserById(int userId) {
		return dslContext.selectFrom(table).where(table.ID.eq(userId)).fetchOne();
	}

}
