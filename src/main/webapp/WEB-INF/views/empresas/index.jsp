<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<div data-options="region:'north',border:false">
    <div style="border:1px solid #ddd">
          
        <a href="empresas/report" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">PDF <spring:message code="label.report" /></a>
    </div>
</div>
<div data-options="region:'center',border:false" >
    <table id="dg-empresa" title="Empresa" class="easyui-datagrid" 
           url="./empresas/list"
           toolbar="#toolbar-empresa" pagination="true"
           rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="id" width="50">Id</th>
                <th field="fantasia" width="50" >Fantasia</th>
<th field="cnpj" width="50" >Cnpj</th>
<th field="criacao" width="50" data-options="formatter:formatDateTime">Criacao</th>
<th field="normal" width="50" >Normal</th>
<th field="status" width="50" >Status</th>
<th field="created_at" width="50" data-options="formatter:formatDateTime">Created_at</th>
<th field="updated_at" width="50" data-options="formatter:formatDateTime">Updated_at</th>

            </tr>
        </thead>
    </table>
    <div id="toolbar-empresa">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newEmpresa()"><spring:message code='label.new'/></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editEmpresa()"><spring:message code='label.edit'/></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyEmpresa()"><spring:message code='label.destroy'/></a>
    </div>

    <%@include file="form.jsp"%> 
</div>

 


