-- Oracle Application Express 4.0.2.00.09 SQL Script Export file
-- Exported 17:34 Tuesday October 28, 2014 by: EJEMPLO_JAVA_TARDE
-- Scripts included:
--      EJEMPLO_JAVA_TARDE
--      EJEMPLO_JAVA_TARDE
 
set define off
set verify off
set serveroutput on size 1000000
set feedback off
WHENEVER SQLERROR EXIT SQL.SQLCODE ROLLBACK
begin wwv_flow.g_import_in_progress := true; end; 
/
 
--       AAAA       PPPPP   EEEEEE  XX      XX
--      AA  AA      PP  PP  EE       XX    XX
--     AA    AA     PP  PP  EE        XX  XX
--    AAAAAAAAAA    PPPPP   EEEE       XXXX
--   AA        AA   PP      EE        XX  XX
--  AA          AA  PP      EE       XX    XX
--  AA          AA  PP      EEEEEE  XX      XX
prompt  Set Credentials...
 
begin
 
  -- Assumes you are running the script connected to SQL*Plus as the Oracle user APEX_040000 or as the owner (parsing schema) of the application.
  wwv_flow_api.set_security_group_id(p_security_group_id=>nvl(wwv_flow_application_install.get_workspace_id,4779004163835735));
 
end;
/

begin wwv_flow.g_import_in_progress := true; end;
/
begin 

select value into wwv_flow_api.g_nls_numeric_chars from nls_session_parameters where parameter='NLS_NUMERIC_CHARACTERS';

end;

/
begin execute immediate 'alter session set nls_numeric_characters=''.,''';

end;

/
begin wwv_flow.g_browser_language := 'en'; end;
/
prompt  Check Compatibility...
 
begin
 
-- This date identifies the minimum version required to import this file.
wwv_flow_api.set_version(p_version_yyyy_mm_dd=>'2010.05.13');
 
end;
/

begin wwv_flow.g_user := nvl(wwv_flow.g_user,'EJEMPLO_JAVA_TARDE'); end;
/
--application/sql/scripts
prompt ...exporting script file
--
begin
    wwv_flow_api.g_varchar2_table := wwv_flow_api.empty_varchar2_table;
    wwv_flow_api.g_varchar2_table(1) := '44524F50205441424C45202241555F4D4154524943554C41223B0A2F0A44524F50205441424C45202241555F5449504F4D4154524943554C41223B0A2F0A44524F50205441424C45202241555F5045524D49534F223B0A2F0A44524F50205441424C4520';
    wwv_flow_api.g_varchar2_table(2) := '2241555F414C554D4E4F223B0A2F0A202020200A2020202064726F702053455155454E4345202241555F414C554D4E4F5F534551220A2F200A2020202064726F702053455155454E4345202241555F4D4154524943554C415F534551220A2F2020200A20';
    wwv_flow_api.g_varchar2_table(3) := '20202064726F702053455155454E4345202241555F5045524D49534F5F534551220A2F2020200A2020202064726F702053455155454E4345202241555F5449504F4D4154524943554C415F534551220A2F202020';
 
end;
/

 
declare
  l_name   varchar2(255);
begin
  l_name   := 'F5003504580628354/autoescuela eliminar todo';
  wwv_flow_api.import_script(
    p_name          => l_name,
    p_varchar2_table=> wwv_flow_api.g_varchar2_table,
    p_pathid=> null,
    p_filename=> 'autoescuela eliminar todo',
    p_title=> 'autoescuela eliminar todo',
    p_mime_type=> 'text/plain',
    p_dad_charset=> '',
    p_deleted_as_of=> to_date('00010101000000','YYYYMMDDHH24MISS'),
    p_content_type=> 'BLOB',
    p_language=> '',
    p_description=> '',
    p_file_type=> 'SCRIPT',
    p_file_charset=> 'utf-8');
 
end;
/

begin
    wwv_flow_api.g_varchar2_table := wwv_flow_api.empty_varchar2_table;
    wwv_flow_api.g_varchar2_table(1) := '2D2D44524F50205441424C4520494620455849535453202741555F414C554D4E4F270A2D2D2F20455354412053454E54454E43494120444520542D53514C204E4F2046554E43494F4E4120454E204F5241434C450A200A0A435245415445207461626C65';
    wwv_flow_api.g_varchar2_table(2) := '202241555F414C554D4E4F2220280A2020202022494422202020202020202020204E554D42455228352C3029204E4F54204E554C4C2C0A20202020224E4F4D42524522202020202020564152434841523228353029204E4F54204E554C4C2C0A20202020';
    wwv_flow_api.g_varchar2_table(3) := '224150454C4C49444F532220202056415243484152322831303029204E4F54204E554C4C2C0A2020202022444E4922202020202020202020564152434841523228353029204E4F54204E554C4C2C0A202020202254454C45464F4E4F2220202020564152';
    wwv_flow_api.g_varchar2_table(4) := '434841523228323029204E4F54204E554C4C2C0A202020202245535441444F222020202020205641524348415232283530292C0A2020202022434F4D454E544152494F532220564152434841523228323530292C0A20202020636F6E73747261696E7420';
    wwv_flow_api.g_varchar2_table(5) := '202241555F414C554D4E4F5F504B22207072696D617279206B6579202822494422290A290A2F0A0A4352454154452073657175656E6365202241555F414C554D4E4F5F53455122200A2F0A0A4352454154452074726967676572202242495F41555F414C';
    wwv_flow_api.g_varchar2_table(6) := '554D4E4F2220200A20206265666F726520696E73657274206F6E202241555F414C554D4E4F2220202020202020202020202020200A2020666F72206561636820726F77200A626567696E20200A20206966203A4E45572E22494422206973206E756C6C20';
    wwv_flow_api.g_varchar2_table(7) := '7468656E0A2020202073656C656374202241555F414C554D4E4F5F534551222E6E65787476616C20696E746F203A4E45572E224944222066726F6D206475616C3B0A2020656E642069663B0A656E643B0A2F2020200A0A2D2D44524F50205441424C4520';
    wwv_flow_api.g_varchar2_table(8) := '494620455849535453202241555F5045524D49534F223B0A0A435245415445207461626C65202241555F5045524D49534F2220280A20202020224944222020202020202020204E554D42455228352C3029204E4F54204E554C4C2C0A202020202256414C';
    wwv_flow_api.g_varchar2_table(9) := '4F52222020202020205641524348415232283529204E4F54204E554C4C2C0A20202020224445534352495043494F4E22205641524348415232283530292C0A20202020636F6E73747261696E7420202241555F5045524D49534F5F504B22207072696D61';
    wwv_flow_api.g_varchar2_table(10) := '7279206B6579202822494422290A290A2F0A0A4352454154452073657175656E6365202241555F5045524D49534F5F53455122200A2F0A0A4352454154452074726967676572202242495F41555F5045524D49534F2220200A20206265666F726520696E';
    wwv_flow_api.g_varchar2_table(11) := '73657274206F6E202241555F5045524D49534F2220202020202020202020202020200A2020666F72206561636820726F77200A626567696E20200A20206966203A4E45572E22494422206973206E756C6C207468656E0A2020202073656C656374202241';
    wwv_flow_api.g_varchar2_table(12) := '555F5045524D49534F5F534551222E6E65787476616C20696E746F203A4E45572E224944222066726F6D206475616C3B0A2020656E642069663B0A656E643B0A2F2020200A0A2D2D44524F50205441424C4520494620455849535453202241555F544950';
    wwv_flow_api.g_varchar2_table(13) := '4F4D4154524943554C41223B0A0A435245415445207461626C65202241555F5449504F4D4154524943554C412220280A20202020224944222020202020202020204E554D42455228352C30292C0A202020202256414C4F52222020202020205641524348';
    wwv_flow_api.g_varchar2_table(14) := '415232283530292C0A20202020636F6E73747261696E7420202241555F5449504F4D4154524943554C415F504B22207072696D617279206B6579202822494422290A290A2F0A0A4352454154452073657175656E6365202241555F5449504F4D41545249';
    wwv_flow_api.g_varchar2_table(15) := '43554C415F53455122200A2F0A0A4352454154452074726967676572202242495F41555F5449504F4D4154524943554C412220200A20206265666F726520696E73657274206F6E202241555F5449504F4D4154524943554C412220202020202020202020';
    wwv_flow_api.g_varchar2_table(16) := '202020200A2020666F72206561636820726F77200A626567696E20200A20206966203A4E45572E22494422206973206E756C6C207468656E0A2020202073656C656374202241555F5449504F4D4154524943554C415F534551222E6E65787476616C2069';
    wwv_flow_api.g_varchar2_table(17) := '6E746F203A4E45572E224944222066726F6D206475616C3B0A2020656E642069663B0A656E643B0A2F2020200A0A2D2D44524F50205441424C4520494620455849535453202241555F4D4154524943554C41223B0A0A435245415445207461626C652022';
    wwv_flow_api.g_varchar2_table(18) := '41555F4D4154524943554C412220280A20202020224944222020202020202020202020202020204E554D42455228352C3029204E4F54204E554C4C2C0A202020202249445F414C554D4E4F2220202020202020204E554D42455228352C3029204E4F5420';
    wwv_flow_api.g_varchar2_table(19) := '4E554C4C2C0A202020202249445F5045524D49534F22202020202020204E554D42455228352C3029204E4F54204E554C4C2C0A202020202249445F5449504F4D4154524943554C4122204E554D42455228352C3029204E4F54204E554C4C2C0A20202020';
    wwv_flow_api.g_varchar2_table(20) := '2246454348415F414C54412220202020202020444154452044454641554C5420535953444154452C0A202020202246454348415F42414A412220202020202020444154452C0A20202020224D4F5449564F5F42414A412220202020202056415243484152';
    wwv_flow_api.g_varchar2_table(21) := '322832303030292C0A20202020636F6E73747261696E7420202241555F4D4154524943554C415F504B22207072696D617279206B6579202822494422290A290A2F0A0A4352454154452073657175656E6365202241555F4D4154524943554C415F534551';
    wwv_flow_api.g_varchar2_table(22) := '22200A2F0A0A4352454154452074726967676572202242495F41555F4D4154524943554C412220200A20206265666F726520696E73657274206F6E202241555F4D4154524943554C412220202020202020202020202020200A2020666F72206561636820';
    wwv_flow_api.g_varchar2_table(23) := '726F77200A626567696E20200A20206966203A4E45572E22494422206973206E756C6C207468656E0A2020202073656C656374202241555F4D4154524943554C415F534551222E6E65787476616C20696E746F203A4E45572E224944222066726F6D2064';
    wwv_flow_api.g_varchar2_table(24) := '75616C3B0A2020656E642069663B0A656E643B0A2F2020200A0A414C544552205441424C45202241555F4D4154524943554C41222041444420434F4E53545241494E54202241555F4D4154524943554C415F464B22200A464F524549474E204B45592028';
    wwv_flow_api.g_varchar2_table(25) := '2249445F414C554D4E4F22290A5245464552454E434553202241555F414C554D4E4F22202822494422290A4F4E2044454C45544520434153434144450A0A2F0A414C544552205441424C45202241555F4D4154524943554C41222041444420434F4E5354';
    wwv_flow_api.g_varchar2_table(26) := '5241494E54202241555F4D4154524943554C415F464B3222200A464F524549474E204B455920282249445F5045524D49534F22290A5245464552454E434553202241555F5045524D49534F22202822494422290A0A2F0A414C544552205441424C452022';
    wwv_flow_api.g_varchar2_table(27) := '41555F4D4154524943554C41222041444420434F4E53545241494E54202241555F4D4154524943554C415F464B3322200A464F524549474E204B455920282249445F5449504F4D4154524943554C4122290A5245464552454E434553202241555F544950';
    wwv_flow_api.g_varchar2_table(28) := '4F4D4154524943554C4122202822494422290A0A2F';
 
end;
/

 
declare
  l_name   varchar2(255);
begin
  l_name   := '5003812074628357/autoescuela generar BD';
  wwv_flow_api.import_script(
    p_name          => l_name,
    p_varchar2_table=> wwv_flow_api.g_varchar2_table,
    p_pathid=> null,
    p_filename=> 'autoescuela generar BD',
    p_title=> 'autoescuela generar BD',
    p_mime_type=> 'text/plain',
    p_dad_charset=> '',
    p_deleted_as_of=> to_date('00010101000000','YYYYMMDDHH24MISS'),
    p_content_type=> 'BLOB',
    p_language=> '',
    p_description=> '',
    p_file_type=> 'SCRIPT',
    p_file_charset=> 'utf-8');
 
end;
/

--commit;
begin 
execute immediate 'begin dbms_session.set_nls( param => ''NLS_NUMERIC_CHARACTERS'', value => '''''''' || replace(wwv_flow_api.g_nls_numeric_chars,'''''''','''''''''''') || ''''''''); end;';
end;
/
set verify on
set feedback on
prompt  ...done
