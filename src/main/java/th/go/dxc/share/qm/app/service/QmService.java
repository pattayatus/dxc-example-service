package th.go.dxc.share.qm.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import th.go.dxc.share.exception.BadGatewayException;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.exception.InternalServerErrorException;
import th.go.dxc.share.exception.NotFoundException;
import th.go.dxc.share.qm.app.model.QmModel;

public interface QmService<MODEL extends QmModel,MODEL_FILTER> {
	public Page<MODEL> findAll(MODEL_FILTER filter,Pageable pageable) throws BadRequestException,BadGatewayException,InternalServerErrorException;
	public Page<MODEL> findByCriteria(String criteria,Pageable pageable) throws BadRequestException,BadGatewayException,InternalServerErrorException;
	public Optional<MODEL> findById(String id) throws NotFoundException,BadRequestException,BadGatewayException,InternalServerErrorException;
}