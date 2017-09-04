webpackJsonp([4,16],{232:function(e,t,a){(function(e){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}function s(){return e.ajax({url:commonBaseUrl+"/department/findByPage.htm",type:"get",dataType:"json",data:{d:"",pageNo:1,pageSize:2e4}}).then(function(e){return e.success?e.resultMap:(d.default.publish("showMsg",["wrong",e.description]),e)})}function l(){return e.ajax({url:commonBaseUrl+"/employee/findByPage.htm",type:"get",dataType:"json",data:{d:"",pageNo:1,pageSize:2e4}}).then(function(e){return e.success?e.resultMap:(d.default.publish("showMsg",["wrong",e.description]),e)})}function r(){return e.ajax({url:commonBaseUrl+"/classify/findByPage.htm",type:"get",dataType:"json",data:{d:"",pageNo:1,pageSize:2e4}}).then(function(e){return e.success?e.resultMap:(d.default.publish("showMsg",["wrong",e.description]),e)})}function i(){return e.ajax({url:commonBaseUrl+"/product/findByPage.htm",type:"get",dataType:"json",data:{d:JSON.stringify({classifyId:"",name:""}),pageNo:1,pageSize:2e4}}).then(function(e){return e.success?e.resultMap:(d.default.publish("showMsg",["wrong",e.description]),e)})}function c(t){var a=t||"";return e.ajax({url:commonBaseUrl+"/employee/findByPage.htm",type:"get",dataType:"json",data:{d:JSON.stringify({departmentId:a}),pageNo:1,pageSize:2e4}}).then(function(e){return e.success?e.resultMap:(d.default.publish("showMsg",["wrong",e.description]),e)})}function u(t,a){var n=localStorage.type,s=1;return 2==n?s=1:3==n&&(s=2),s=a||s,e.ajax({url:commonBaseUrl+"/order/findByOrderNo.htm",type:"get",dataType:"json",data:{d:JSON.stringify({orderNo:t,type:s})}}).then(function(e){return e.success?e.resultMap:(d.default.publish("showMsg",["wrong",e.description]),e)})}Object.defineProperty(t,"__esModule",{value:!0}),t.departList=s,t.employeeList=l,t.classifyList=r,t.productList=i,t.memberList=c,t.orderDetail=u;var o=a(210),d=n(o)}).call(t,a(72))},419:function(e,t,a){(function(t,n){"use strict";function s(e){return e&&e.__esModule?e:{default:e}}var l=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&(e[n]=a[n])}return e},r=a(71),i=s(r),c=a(226),u=s(c),o=a(210),d=s(o),m=a(7);a(228);var p=a(232),f=["","男","女","通用"],g=["","成人","儿童","通用"],h=t.createClass({displayName:"Depart",getInitialState:function(){return{listRequest:{classifyId:"",name:""},pager:{currentPage:1,pageSize:10,totalNum:0},selectValue:[{key:"全部",value:""}],list:[]}},componentDidMount:function(){this.getList(),this.classifyList()},classifyList:function(){var e=this,t=this.state.selectValue;(0,p.classifyList)().then(function(a){a.rows&&a.rows.map(function(e){t.push({key:e.classifyName,value:e.id})}),e.setState({selectValue:t})})},getList:function(){var e=arguments.length<=0||void 0===arguments[0]?1:arguments[0],t=this,a=this.state,s=a.pager,l=a.listRequest;n.ajax({url:commonBaseUrl+"/product/findByPage.htm",type:"get",dataType:"json",data:{d:JSON.stringify(l),pageNo:e,pageSize:10},success:function(a){a.success?(s.currentPage=e,s.totalNum=a.resultMap.iTotalDisplayRecords,t.setState({list:a.resultMap.rows,pager:s})):(s.currentPage=1,s.totalNum=0,t.setState({list:[],pager:s}))}})},add:function(){m.hashHistory.push("/commodity/add/")},delete:function(e){var t=this;RUI.DialogManager.confirm({message:"您确定要删除吗？?",title:"删除成员",submit:function(){n.ajax({url:commonBaseUrl+"/product/delete.htm",type:"post",dataType:"json",data:{d:JSON.stringify({productId:e})},success:function(e){e.success?(d.default.publish("showMsg",["success","删除成功"]),t.getList()):d.default.publish("showMsg",["wrong",e.description])}})}})},applySex:function(e){return f[e]},applyCrowd:function(e){return g[e]},select:function(e){var t=this,a=this.state.listRequest;a.classifyId=e.value,this.setState({listRequest:a},function(){t.getList()})},search:function(){this.getList()},inputChange:function(e){var t=this.state.listRequest;t.name=e.target.value,this.setState({listRequest:t})},render:function(){var e=this,a=this.state,n=a.list,s=a.pager,r=a.selectValue;return t.createElement(i.default,{currentKey:"5",defaultOpen:"1",bread:["产品库存","产品管理"]},t.createElement("div",{className:"depart-content"},t.createElement("div",{className:"tbn-div"},t.createElement("label",{htmlFor:""},"分类："),t.createElement(RUI.Select,{data:r,value:{key:"全部",value:""},stuff:!0,callback:this.select,className:"rui-theme-1 w-120"}),t.createElement("label",{htmlFor:""},"名称："),t.createElement(RUI.Input,{onChange:this.inputChange,className:"w-150"}),t.createElement(RUI.Button,{className:"primary",onClick:this.search},"搜索"),t.createElement(RUI.Button,{className:"add-btn primary",onClick:this.add},"添加")),t.createElement("table",{className:"table"},t.createElement("thead",null,t.createElement("tr",null,t.createElement("td",null,"产品图片"),t.createElement("td",null,"产品名称"),t.createElement("td",null,"所属分类"),t.createElement("td",null,"适用性别"),t.createElement("td",null,"适用人群"),t.createElement("td",null,"鞋码区间"),t.createElement("td",null,"操作方式"))),t.createElement("tbody",null,n.length>0&&n.map(function(a,n){return t.createElement("tr",{key:n},t.createElement("td",null,t.createElement("img",{className:"commodity-img",src:a.url,alt:""})),t.createElement("td",null,a.name),t.createElement("td",null,a.classifyName),t.createElement("td",null,e.applySex(a.applySex)),t.createElement("td",null,e.applyCrowd(a.applyCrowd)),t.createElement("td",null,a.minCode+"~"+a.maxCode),t.createElement("td",null,t.createElement(m.Link,{to:"/commodity/add/?id="+a.id,className:"handle-a"},"修改"),t.createElement("a",{href:"javascript:;",className:"handle-a",onClick:e.delete.bind(e,a.id)},"删除")))}))),0==n.length&&t.createElement("div",{className:"no-data"},"暂时没有数据哦"),t.createElement(u.default,l({onPage:this.getList},s))))}});e.exports=h}).call(t,a(2),a(72))}});