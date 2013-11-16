    <div id="dlg-empresa" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons-empresa">
        <div class="ftitle">Empresa Information</div>
        <form id="fm-empresa" method="post" novalidate class="fm">
                <div class="fitem">
<label>Fantasia:</label>
<input name="fantasia" type="text">
</div><div class="fitem">
<label>Cnpj:</label>
<input name="cnpj" type="text">
</div><div class="fitem">
<label>Criacao:</label>
<input name="criacao" class="easyui-datetimebox" data-options="parser:parseDate" type="text">
</div><div class="fitem">
<label>Normal:</label>
<input name="normal" type="checkbox" value="true">
<input name="normal" type="hidden" value="false">
</div><div class="fitem">
<label>Status:</label>
<input name="status" class="easyui-numberbox" type="text">
</div>
        </form>
    </div>
    <div id="dlg-buttons-empresa">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEmpresa()"><spring:message code='label.save'/></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-empresa').dialog('close')"><spring:message code='label.cancel'/></a>
    </div>
    </div>
    <script type="text/javascript">
        var url;
        function newEmpresa(){
            $('#dlg-empresa').dialog('open').dialog('setTitle',"<spring:message code='label.new'/> Empresa");
            $('#fm-empresa').form('clear');
            url = './empresas/save';
        }
        function editEmpresa(){
            var row = $('#dg-empresa').datagrid('getSelected');
            if (row){
                $('#dlg-empresa').dialog('open').dialog('setTitle',"<spring:message code='label.new'/> Empresa");
                $('#fm-empresa').form('load',row);
                url = './empresas/update/'+row.id;
            }
        }
        function saveEmpresa(){
            $('#fm-empresa').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
            
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg-empresa').dialog('close');        // close the dialog
                        $('#dg-empresa').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyEmpresa(){
            var row = $('#dg-empresa').datagrid('getSelected');
            if (row){
                $.messager.confirm("<spring:message code='label.confirm'/>","<spring:message code='label.message.destroy' arguments='empresa'/>",function(r){
                    if (r){
                        $.post('./empresas/destroy/'+row.id,function(result){
                            if (result.success){
                                $('#dg-empresa').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
  

