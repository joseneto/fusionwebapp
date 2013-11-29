<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<div data-options="region:'north',border:false">
    <div style="border:1px solid #ddd">
          
        <a href="people/report" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">PDF <spring:message code="label.report" /></a>
    </div>
</div>
<div data-options="region:'center',border:false" >
    <table id="dg-person" title="Person" class="easyui-datagrid" 
           url="./people/list"
           toolbar="#toolbar-person" pagination="true"
           rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="id" width="50">Id</th>
                <th field="first_name" width="50" >First_name</th>
<th field="last_name" width="50" >Last_name</th>
<th field="created_at" width="50" data-options="formatter:formatDateTime">Created_at</th>
<th field="updated_at" width="50" data-options="formatter:formatDateTime">Updated_at</th>

            </tr>
        </thead>
    </table>
    <div id="toolbar-person">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPerson()"><spring:message code='label.new'/></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPerson()"><spring:message code='label.edit'/></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyPerson()"><spring:message code='label.destroy'/></a>
    </div>

    <%@include file="form.jsp"%> 
</div>

 


