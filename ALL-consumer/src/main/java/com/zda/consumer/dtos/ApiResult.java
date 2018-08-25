package com.zda.consumer.dtos;

public class ApiResult<E> {
	private E resulT;
	private String msg;
	private Boolean successful;
	public <T> ApiResult() {
		super();
	}
	public <T> ApiResult(E _resulT,String _msg, Boolean _successful) {
		this.resulT = _resulT;
		this.msg = _msg;
		this.successful = _successful;
	}

	public ApiResult<E> setSuccess(E result) {
		this.resulT = result;
		this.successful = true;
		return this;
	}

	public E getResulT() {
		return resulT;
	}

	public void setResulT(E resulT) {
		this.resulT = resulT;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

}
