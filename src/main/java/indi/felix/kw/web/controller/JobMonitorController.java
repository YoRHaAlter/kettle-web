package indi.felix.kw.web.controller;

import indi.felix.kw.common.toolkit.Constant;
import indi.felix.kw.core.dto.BootTablePage;
import indi.felix.kw.core.dto.ResultDto;
import indi.felix.kw.core.model.KUser;
import indi.felix.kw.web.service.JobMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/job/monitor/")
public class JobMonitorController {
	
	@Autowired
	private JobMonitorService jobMonitorService;
	
	@RequestMapping("getList.shtml")
	public String getList(Integer offset, Integer limit, HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		BootTablePage list = jobMonitorService.getList(offset, limit, kUser.getuId());
		return ResultDto.success(list);
	}
	
	@RequestMapping("getAllMonitorJob.shtml")
	public String getAllMonitorJob(HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		return ResultDto.success(jobMonitorService.getAllMonitorJob(kUser.getuId()));
	}
	
	@RequestMapping("getAllSuccess.shtml")
	public String getAllSuccess(HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		return ResultDto.success(jobMonitorService.getAllSuccess(kUser.getuId()));
	}
	
	@RequestMapping("getAllFail.shtml")
	public String getAllFail(HttpServletRequest request){
		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		return ResultDto.success(jobMonitorService.getAllFail(kUser.getuId()));
	}
}
