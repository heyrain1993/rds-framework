/**
 * 分页功能js
 */
$(function () {
	var pageUrl = [[${page.url}]];
	var pageNo = [[${page.pageNo}]];
	var pageSize = [[${page.pageSize}]];
	var totalRecord = [[${page.totalRecord}]];
	var totalPage = [[${page.totalPage}]];
	$('#totalRecord').text(totalRecord);
	$('#totalPage').text(totalPage);
    var options={
        alignment:'center',
        currentPage:pageNo,
        numberOfPages:'5',
        totalPages:totalPage,
        bootstrapMajorVersion:'3',
        shouldShowPage:true,
        itemTexts: function(type,page,current){
        	switch(type){
        	case "first":
        		return "首页";
        	case "prev":
        		return "上一页";
        	case "next":
        		return "下一页";
        	case "last":
        		return "末页";
        	case "page":
        		return page;
        	}
        },
        itemContainerClass:function(type,page,current){//设置当前页码激活，颜色变深
        	if(type == "page"){
        		return (page === current)? "active" :"";
        	}
        },
        onPageClicked:function(event, originalEvent, type, page){
        	$.ajax({
        		url:pageUrl + "?pageNo="+page,
        		type:"GET"
        	});
        }
    };
    $('#page').bootstrapPaginator(options);
});