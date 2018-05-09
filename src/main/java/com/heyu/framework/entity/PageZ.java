package com.heyu.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageZ<T>
{
  private int pageNo;//当前页
  private int pageSize;//每页数量
  private long count;
  private int first;
  private int last;
  private int prev;
  private int next;
  private boolean firstPage;
  private boolean lastPage;
  private int length;
  private int slider;
  private List<T> list;
  private String orderBy;
  private String funcName;
  private String funcParam;
  private String message;

  public PageZ()
  {
    this.pageNo = 1;
    this.pageSize = 30;

    this.length = 8;
    this.slider = 1;

    this.list = new ArrayList<T>();

    this.orderBy = "";

    this.funcName = "page";

    this.funcParam = "";

    this.message = "";

    this.pageSize = -1;
  }

  public PageZ(HttpServletRequest request, HttpServletResponse response)
  {
    this(request, response, -2);
  }

  public PageZ(HttpServletRequest request, HttpServletResponse response, int defaultPageSize)
  {
    this.pageNo = 1;
    this.pageSize = 30;

    this.length = 8;
    this.slider = 1;

    this.list = new ArrayList<T>();

    this.orderBy = "";

    this.funcName = "page";

    this.funcParam = "";

    this.message = "";

    String no = request.getParameter("pageNo");
/*    if (StringUtils.isNumeric(no)) {
      CookieUtils.setCookie(response, "pageNo", no);
      setPageNo(Integer.parseInt(no));
    } else if (request.getParameter("repage") != null) {
      no = CookieUtils.getCookie(request, "pageNo");
      if (StringUtils.isNumeric(no)) {
        setPageNo(Integer.parseInt(no));
      }
    }*/

    String size = request.getParameter("pageSize");
/*    if (StringUtils.isNumeric(size)) {
      CookieUtils.setCookie(response, "pageSize", size);
      setPageSize(Integer.parseInt(size));
    } else if (request.getParameter("repage") != null) {
      size = CookieUtils.getCookie(request, "pageSize");
      if (StringUtils.isNumeric(size))
        setPageSize(Integer.parseInt(size));
    }
    else if (defaultPageSize != -2) {
      this.pageSize = defaultPageSize;
    }*/

/*    String funcName = request.getParameter("funcName");
    if (StringUtils.isNotBlank(funcName)) {
      CookieUtils.setCookie(response, "funcName", funcName);
      setFuncName(funcName);
    } else if (request.getParameter("repage") != null) {
      funcName = CookieUtils.getCookie(request, "funcName");
      if (StringUtils.isNotBlank(funcName)) {
        setFuncName(funcName);
      }
    }

    String orderBy = request.getParameter("orderBy");
    if (StringUtils.isNotBlank(orderBy))
      setOrderBy(orderBy);*/
  }

  public PageZ(int pageNo, int pageSize)
  {
    this(pageNo, pageSize, 0L);
  }

  public PageZ(int pageNo, int pageSize, long count)
  {
    this(pageNo, pageSize, count, new ArrayList<T>());
  }

  public PageZ(int pageNo, int pageSize, long count, List<T> list)
  {
    this.pageNo = 1;
    this.pageSize = 30;

    this.length = 8;
    this.slider = 1;

    this.list = new ArrayList<T>();

    this.orderBy = "";

    this.funcName = "page";

    this.funcParam = "";

    this.message = "";

    setCount(count);
    setPageNo(pageNo);
    this.pageSize = pageSize;
    this.list = list;
  }

  public void initialize()
  {
    this.first = 1;

    this.last = (int)(this.count / ((this.pageSize < 1) ? 20 : this.pageSize) + this.first - 1L);

    if ((this.count % this.pageSize != 0L) || (this.last == 0)) {
      this.last += 1;
    }

    if (this.last < this.first) {
      this.last = this.first;
    }

    if (this.pageNo <= 1) {
      this.pageNo = this.first;
      this.firstPage = true;
    }

    if (this.pageNo >= this.last) {
      this.pageNo = this.last;
      this.lastPage = true;
    }

    if (this.pageNo < this.last - 1)
      this.next = (this.pageNo + 1);
    else {
      this.next = this.last;
    }

    if (this.pageNo > 1)
      this.prev = (this.pageNo - 1);
    else {
      this.prev = this.first;
    }

    if (this.pageNo < this.first) {
      this.pageNo = this.first;
    }

    if (this.pageNo > this.last)
      this.pageNo = this.last;
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    if (this.pageNo == this.first)
      sb.append(new StringBuilder().append("<li class=\"disabled paginate_button \"><a href=\"javascript:\">&#171; ").append("上一页").append("</a></li>\n").toString());
    else {
      sb.append(new StringBuilder().append("<li class=\"paginate_button\"><a href=\"javascript:\" onclick=\"").append(this.funcName).append("(").append(this.prev).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');\">&#171; ").append("上一页").append("</a></li>\n").toString());
    }

    int begin = this.pageNo - (this.length / 2);

    if (begin < this.first) {
      begin = this.first;
    }

    int end = begin + this.length - 1;

    if (end >= this.last) {
      end = this.last;
      begin = end - this.length + 1;
      if (begin < this.first) {
        begin = this.first;
      }
    }

/*    if (begin > this.first) {
      i = 0;
      for (i = this.first; (i < this.first + this.slider) && (i < begin); ++i) {
        sb.append(new StringBuilder().append("<li class=\"paginate_button\"><a href=\"javascript:\" onclick=\"").append(this.funcName).append("(").append(i).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');\">").append(i + 1 - this.first).append("</a></li>\n").toString());
      }

      if (i < begin) {
        sb.append("<li class=\"disabled paginate_button\"><a href=\"javascript:\">...</a></li>\n");
      }
    }*/

    for (int i = begin; i <= end; ++i) {
      if (i == this.pageNo) {
        sb.append(new StringBuilder().append("<li class=\"active paginate_button\"><a href=\"javascript:\">").append(i + 1 - this.first).append("</a></li>\n").toString());
      }
      else {
        sb.append(new StringBuilder().append("<li class=\"paginate_button\"><a href=\"javascript:\" onclick=\"").append(this.funcName).append("(").append(i).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');\">").append(i + 1 - this.first).append("</a></li>\n").toString());
      }

    }

    if (this.last - end > this.slider) {
      sb.append("<li class=\"disabled paginate_button\"><a href=\"javascript:\">...</a></li>\n");
      end = this.last - this.slider;
    }

/*    for (i = end + 1; i <= this.last; ++i) {
      sb.append(new StringBuilder().append("<li class=\"paginate_button\"><a href=\"javascript:\" onclick=\"").append(this.funcName).append("(").append(i).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');\">").append(i + 1 - this.first).append("</a></li>\n").toString());
    }

    if (this.pageNo == this.last)
      sb.append(new StringBuilder().append("<li class=\"disabled paginate_button\"><a href=\"javascript:\">").append(I18n.i18nMessage("common.nextPage")).append(" &#187;</a></li>\n").toString());
    else {
      sb.append(new StringBuilder().append("<li class=\"paginate_button\"><a href=\"javascript:\" onclick=\"").append(this.funcName).append("(").append(this.next).append(",").append(this.pageSize).append(",'").append(this.funcParam).append("');\">")
        .append(I18n.i18nMessage("common.nextPage"))
        .append(" &#187;</a></li>\n").toString());
    }*/

/*    sb.append(new StringBuilder().append("<li class=\"disabled controls\"><a id=\"page-text\" href=\"javascript:\">").append(I18n.i18nMessage("common.current")).append(" ").toString());
    sb.append(new StringBuilder().append("<input type=\"text\" value=\"").append(this.pageNo).append("\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)").toString());
    sb.append(new StringBuilder().append(this.funcName).append("(this.value,").append(this.pageSize).append(",'").append(this.funcParam).append("');\" onclick=\"this.select();\"/> / ").toString());
    sb.append(new StringBuilder().append("<input type=\"text\" value=\"").append(this.pageSize).append("\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)").toString());
    sb.append(new StringBuilder().append(this.funcName).append("(").append(this.pageNo).append(",this.value,'").append(this.funcParam).append("');\" onclick=\"this.select();\"/> ").append(I18n.i18nMessage("common.tiao")).append(" ").toString());
    sb.append(new StringBuilder().append(I18n.i18nMessage("common.total")).append(" ").append(this.count).append(" ").append(I18n.i18nMessage("common.tiao")).append((this.message != null) ? this.message : "").append("</a></li>\n").toString());
*/
    sb.insert(0, "<ul class=\"pagination\">\n").append("</ul>\n");

    sb.append("<div style=\"clear:both;\"></div>");

    return sb.toString();
  }

  public String getHtml()
  {
    return toString();
  }

  public long getCount()
  {
    return this.count;
  }

  public void setCount(long count)
  {
    this.count = count;
    if (this.pageSize >= count)
      this.pageNo = 1;
  }

  public int getPageNo()
  {
    return this.pageNo;
  }

  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = ((pageSize <= 0) ? 10 : pageSize);
  }

  @JsonIgnore
  public int getFirst()
  {
    return this.first;
  }

  @JsonIgnore
  public int getLast()
  {
    return this.last;
  }

  @JsonIgnore
  public int getTotalPage()
  {
    return getLast();
  }

  @JsonIgnore
  public boolean isFirstPage()
  {
    return this.firstPage;
  }

  @JsonIgnore
  public boolean isLastPage()
  {
    return this.lastPage;
  }

  @JsonIgnore
  public int getPrev()
  {
    if (isFirstPage()) {
      return this.pageNo;
    }
    return (this.pageNo - 1);
  }

  @JsonIgnore
  public int getNext()
  {
    if (isLastPage()) {
      return this.pageNo;
    }
    return (this.pageNo + 1);
  }

  public List<T> getList()
  {
    return this.list;
  }

  public PageZ<T> setList(List<T> list)
  {
    this.list = list;
    initialize();
    return this;
  }

  @JsonIgnore
  public String getOrderBy()
  {
    String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

    Pattern sqlPattern = Pattern.compile(reg, 2);
    if (sqlPattern.matcher(this.orderBy).find()) {
      return "";
    }
    return this.orderBy;
  }

  public void setOrderBy(String orderBy)
  {
    this.orderBy = orderBy;
  }

  @JsonIgnore
  public String getFuncName()
  {
    return this.funcName;
  }

  public void setFuncName(String funcName)
  {
    this.funcName = funcName;
  }

  @JsonIgnore
  public String getFuncParam()
  {
    return this.funcParam;
  }

  public void setFuncParam(String funcParam)
  {
    this.funcParam = funcParam;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  @JsonIgnore
  public boolean isDisabled()
  {
    return (this.pageSize == -1);
  }

  @JsonIgnore
  public boolean isNotCount()
  {
    return (this.count == -1L);
  }

  public int getFirstResult()
  {
    int firstResult = (getPageNo() - 1) * getPageSize();
    if (firstResult >= getCount()) {
      firstResult = 0;
    }
    return firstResult;
  }

  public int getMaxResults()
  {
    return getPageSize();
  }
}
