webpackJsonp([3,16],{418:function(e,t,a){(function(t,s){"use strict";function l(e){return e&&e.__esModule?e:{default:e}}var n=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var s in a)Object.prototype.hasOwnProperty.call(a,s)&&(e[s]=a[s])}return e},i=a(220),r=l(i),c=a(71),u=l(c),d=a(226),o=l(d),f=a(227),m=l(f),h=a(210),p=l(h);a(228);var g=t.createClass({displayName:"Depart",getInitialState:function(){return{title:"添加分类",request:{classifyName:"",id:""},type:"",list:[],pager:{currentPage:1,pageSize:20,totalNum:0}}},componentDidMount:function(){this.getList()},getList:function(){var e=arguments.length<=0||void 0===arguments[0]?1:arguments[0],t=this,a=this.state.pager;s.ajax({url:commonBaseUrl+"/classify/findByPage.htm",type:"get",dataType:"json",data:{d:"",pageNo:e,pageSize:20},success:function(s){s.success?(a.currentPage=e,a.totalNum=s.resultMap.iTotalDisplayRecords,t.setState({list:s.resultMap.rows,pager:a})):(a.currentPage=1,a.totalNum=0,t.setState({list:[],pager:a}))}})},doPage:function(){},add:function(){var e=this,t=this.state.request;delete t.id,t.classifyName="",this.setState({title:"添加分类",type:"add",request:t},function(){e.refs.dialog.show()})},dialogSubmit:function(){var e=this,t=this.state,a=t.request,l=t.type;if(!a.classifyName)return p.default.publish("showMsg",["wrong","请输入类别名称"]),!1;var n="add"==l?"/classify/add.htm":"/classify/update.htm";s.ajax({url:commonBaseUrl+n,dataType:"json",type:"post",data:{d:JSON.stringify(a)},success:function(t){t.success?(p.default.publish("showMsg",["success","add"==l?"添加成功":"修改成功"]),e.getList()):p.default.publish("showMsg",["wrong",t.description])}})},handleInput:function(e,t){var a=this.state.request;a[e]=t.target.value,this.setState({request:a})},modify:function(e){var t=this,a=this.state.request,s=JSON.stringify(e),l=JSON.parse(s);a=l,this.setState({title:"修改分类",type:"edit",request:a},function(){t.refs.dialog.show()})},delete:function(e){var t=this;r.default.DialogManager.confirm({message:"您确定要删除吗？?",title:"删除分类",submit:function(){s.ajax({url:commonBaseUrl+"/classify/delete.htm",type:"post",dataType:"json",data:{d:JSON.stringify({classifyId:e})},success:function(e){e.success?(p.default.publish("showMsg",["success","删除成功"]),t.getList()):p.default.publish("showMsg",["wrong",e.description])}})}})},render:function(){var e=this,a=this.state,s=a.list,l=a.pager,i=a.request;return t.createElement(u.default,{currentKey:"4",defaultOpen:"1",bread:["产品库存","分类管理"]},t.createElement("div",{className:"depart-content"},t.createElement("div",{className:"tbn-div"},t.createElement(r.default.Button,{className:"add-btn primary",onClick:this.add},"添加")),t.createElement("table",{className:"table"},t.createElement("thead",null,t.createElement("tr",null,t.createElement("td",null,"类别名称"),t.createElement("td",null,"操作方式"))),t.createElement("tbody",null,s.length>0&&s.map(function(a,s){return t.createElement("tr",{key:s},t.createElement("td",null,a.classifyName),t.createElement("td",null,t.createElement("a",{href:"javascript:;",className:"handle-a",onClick:e.modify.bind(e,a)},"修改"),t.createElement("a",{href:"javascript:;",className:"handle-a",onClick:e.delete.bind(e,a.id)},"删除")))}))),0==s.length&&t.createElement("div",{className:"no-data"},"暂时没有数据哦"),t.createElement(o.default,n({onPage:this.getList},l)),t.createElement(r.default.Dialog,{ref:"dialog",title:this.state.title,draggable:!1,buttons:"submit,cancel",onCancel:this.dialogCancel,onSubmit:this.dialogSubmit},t.createElement("div",{style:{width:"400px",wordWrap:"break-word"}},t.createElement(m.default,{placeholder:"请输入类别名称",require:!0,value:i.classifyName,onChange:this.handleInput.bind(this,"classifyName"),label:"类别名称："})))))}});e.exports=g}).call(t,a(2),a(72))}});