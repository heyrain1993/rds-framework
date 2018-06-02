package com.heyu.framework.model;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.heyu.framework.utils.StringUtils;

public class Page<T> {

    private Integer pageNo = 1;//当前页

    private Integer pageSize = 10;//每页条数

    private Boolean useFlag = true;//是否使用分页

    private Boolean checkFlag;//检查当前页码是否合法(大于最大页或小于最小页都不合法)

    private Integer totalRecord;//总记录数
    
    private String url;

    private Integer totalPage;//总页数

    //private HashMap<String,Object> params = new HashMap<>();//其他参数封装成Map

    private List<T> list = new ArrayList<>();//对应当前页记录数

    public Page(HttpServletRequest request) {
    	super();
    	if(request != null) {
    		String pageNo = request.getParameter("pageNo");
    		String pageSize = request.getParameter("pageSize");
    		if(!StringUtils.isEmpty(pageNo) ) {
    			this.setPageNo(Integer.parseInt(pageNo));
    		}
    		if(!StringUtils.isEmpty(pageSize)) {
    			this.setPageSize(Integer.parseInt(pageSize));
    			
    		}
    		this.setUrl(request.getRequestURI());
    	}
	}

    public Page() {
    	super();
    }
	public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     * 设置总记录数，并计算总页数
     * @param totalRecord
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
        int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        this.setTotalPage(totalPage);
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
