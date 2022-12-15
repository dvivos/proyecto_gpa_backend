package com.gpa.backendrest.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.gpa.backendrest.app.entity.FormaPago;

public interface IFormaPagoDao extends CrudRepository<FormaPago, Long> {

}
