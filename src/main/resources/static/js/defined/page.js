/**
 * 分页功能js
 */
$(function () {
    var options={
        alignment:'center',
        currentPage:'1',
        numberOfPages:'5',
        totalPages:'10',
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
        }
    };
    $('#page').bootstrapPaginator(options);
});