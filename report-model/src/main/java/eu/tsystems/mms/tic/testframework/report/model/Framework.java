// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: framework.proto

package eu.tsystems.mms.tic.testframework.report.model;

public final class Framework {
  private Framework() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_SuiteContext_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_SuiteContext_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_ClassContext_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_ClassContext_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_TestContext_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_TestContext_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_ExecutionContext_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_ExecutionContext_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_MethodContext_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_MethodContext_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_ContextValues_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_ContextValues_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_PTestStep_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_PTestStep_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_PTestStepAction_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_PTestStepAction_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_PClickPathEvent_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_PClickPathEvent_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_PLogMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_PLogMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_ErrorContext_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_ErrorContext_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_SessionContext_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_SessionContext_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_SessionContext_MetadataEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_SessionContext_MetadataEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_RunConfig_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_RunConfig_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_BuildInformation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_BuildInformation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_StackTraceCause_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_StackTraceCause_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_ScriptSource_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_ScriptSource_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_ScriptSourceLine_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_ScriptSourceLine_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_File_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_File_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_File_MetaEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_File_MetaEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017framework.proto\022\004data\"s\n\014SuiteContext\022" +
      "+\n\016context_values\030\001 \001(\0132\023.data.ContextVa" +
      "lues\022\030\n\020test_context_ids\030\006 \003(\t\022\034\n\024execut" +
      "ion_context_id\030\007 \001(\t\"\361\001\n\014ClassContext\022+\n" +
      "\016context_values\030\001 \001(\0132\023.data.ContextValu" +
      "es\022\032\n\022method_context_ids\030\006 \003(\t\022\027\n\017full_c" +
      "lass_name\030\007 \001(\t\022\031\n\021simple_class_name\030\010 \001" +
      "(\t\022\027\n\017test_context_id\030\t \001(\t\022\034\n\024execution" +
      "_context_id\030\n \001(\t\022\031\n\021test_context_name\030\013" +
      " \001(\t\022\022\n\006merged\030\014 \001(\010B\002\030\001\"\215\001\n\013TestContext" +
      "\022+\n\016context_values\030\001 \001(\0132\023.data.ContextV" +
      "alues\022\031\n\021class_context_ids\030\006 \003(\t\022\030\n\020suit" +
      "e_context_id\030\007 \001(\t\022\034\n\024execution_context_" +
      "id\030\010 \001(\t\"\266\002\n\020ExecutionContext\022+\n\016context" +
      "_values\030\001 \001(\0132\023.data.ContextValues\022\031\n\021su" +
      "ite_context_ids\030\006 \003(\t\022#\n\nrun_config\030\007 \001(" +
      "\0132\017.data.RunConfig\022\022\n\nproject_Id\030\010 \001(\t\022\016" +
      "\n\006job_Id\030\t \001(\t\022\016\n\006run_Id\030\n \001(\t\022\017\n\007task_I" +
      "d\030\013 \001(\t\022%\n\035exclusive_session_context_ids" +
      "\030\014 \003(\t\022 \n\030estimatedTestMethodCount\030\r \001(\005" +
      "\022\'\n\014log_messages\030\016 \003(\0132\021.data.PLogMessag" +
      "e\"\355\005\n\rMethodContext\022+\n\016context_values\030\001 " +
      "\001(\0132\023.data.ContextValues\022%\n\013method_type\030" +
      "\007 \001(\0162\020.data.MethodType\022\022\n\nparameters\030\010 " +
      "\003(\t\022\023\n\013method_tags\030\t \003(\t\022\024\n\014retry_number" +
      "\030\n \001(\005\022\030\n\020method_run_index\030\013 \001(\005\022\023\n\013thre" +
      "ad_name\030\014 \001(\t\022:\n\026failure_corridor_value\030" +
      "\016 \001(\0162\032.data.FailureCorridorValue\022\030\n\020cla" +
      "ss_context_id\030\017 \001(\t\022\034\n\024execution_context" +
      "_id\030\020 \001(\t\0220\n\024non_functional_infos\030\021 \003(\0132" +
      "\022.data.ErrorContext\0220\n\024collected_asserti" +
      "ons\030\022 \003(\0132\022.data.ErrorContext\022\r\n\005infos\030\023" +
      " \003(\t\022\030\n\020priority_message\030\025 \001(\t\022\"\n\032relate" +
      "d_method_context_ids\030\027 \003(\t\022%\n\035depends_on" +
      "_method_context_ids\030\030 \003(\t\022)\n\rerror_conte" +
      "xt\030\031 \001(\0132\022.data.ErrorContext\022#\n\ntest_ste" +
      "ps\030\032 \003(\0132\017.data.PTestStep\022\027\n\017test_contex" +
      "t_id\030\033 \001(\t\022\030\n\020suite_context_id\030\034 \001(\t\022\033\n\023" +
      "session_context_ids\030\035 \003(\t\022\021\n\tvideo_ids\030\036" +
      " \003(\t\022\033\n\023custom_context_json\030  \001(\t\"\307\001\n\rCo" +
      "ntextValues\022\n\n\002id\030\001 \001(\t\022\017\n\007created\030\002 \001(\003" +
      "\022\014\n\004name\030\003 \001(\t\022\022\n\nstart_time\030\004 \001(\003\022\020\n\010en" +
      "d_time\030\005 \001(\003\022\013\n\003swi\030\006 \001(\t\022-\n\rresult_stat" +
      "us\030\007 \001(\0162\026.data.ResultStatusType\022)\n\013exec" +
      "_status\030\010 \001(\0162\024.data.ExecStatusType\"K\n\tP" +
      "TestStep\022\014\n\004name\030\001 \001(\t\0220\n\021test_step_acti" +
      "ons\030\003 \003(\0132\025.data.PTestStepAction\"\244\001\n\017PTe" +
      "stStepAction\022\014\n\004name\030\001 \001(\t\022\021\n\ttimestamp\030" +
      "\003 \001(\003\022/\n\020clickpath_events\030\005 \003(\0132\025.data.P" +
      "ClickPathEvent\022\026\n\016screenshot_ids\030\006 \003(\t\022\'" +
      "\n\014log_messages\030\007 \003(\0132\021.data.PLogMessage\"" +
      "_\n\017PClickPathEvent\022\'\n\004type\030\001 \001(\0162\031.data." +
      "PClickPathEventType\022\017\n\007subject\030\002 \001(\t\022\022\n\n" +
      "session_id\030\003 \001(\t\"k\n\013PLogMessage\022#\n\004type\030" +
      "\001 \001(\0162\025.data.PLogMessageType\022\023\n\013logger_n" +
      "ame\030\002 \001(\t\022\017\n\007message\030\003 \001(\t\022\021\n\ttimestamp\030" +
      "\004 \001(\003\"\273\001\n\014ErrorContext\022)\n\rscript_source\030" +
      "\007 \001(\0132\022.data.ScriptSource\0223\n\027execution_o" +
      "bject_source\030\010 \001(\0132\022.data.ScriptSource\022\020" +
      "\n\010ticketId\030\t \001(\t\022\023\n\013description\030\n \001(\t\022$\n" +
      "\005cause\030\013 \001(\0132\025.data.StackTraceCause\"\337\001\n\016" +
      "SessionContext\022+\n\016context_values\030\001 \001(\0132\023" +
      ".data.ContextValues\022\023\n\013session_key\030\002 \001(\t" +
      "\022\020\n\010provider\030\003 \001(\t\0224\n\010metadata\030\004 \003(\0132\".d" +
      "ata.SessionContext.MetadataEntry\022\022\n\nsess" +
      "ion_id\030\006 \001(\t\032/\n\rMetadataEntry\022\013\n\003key\030\001 \001" +
      "(\t\022\r\n\005value\030\002 \001(\t:\0028\001\"c\n\tRunConfig\022\016\n\006ru" +
      "ncfg\030\001 \001(\t\0221\n\021build_information\030\002 \001(\0132\026." +
      "data.BuildInformation\022\023\n\013report_name\030\003 \001" +
      "(\t\"\250\001\n\020BuildInformation\022\032\n\022build_java_ve" +
      "rsion\030\001 \001(\t\022\025\n\rbuild_os_name\030\002 \001(\t\022\030\n\020bu" +
      "ild_os_version\030\003 \001(\t\022\027\n\017build_user_name\030" +
      "\004 \001(\t\022\025\n\rbuild_version\030\005 \001(\t\022\027\n\017build_ti" +
      "mestamp\030\006 \001(\t\"z\n\017StackTraceCause\022\022\n\nclas" +
      "s_name\030\001 \001(\t\022\017\n\007message\030\002 \001(\t\022\034\n\024stack_t" +
      "race_elements\030\003 \003(\t\022$\n\005cause\030\004 \001(\0132\025.dat" +
      "a.StackTraceCause\"]\n\014ScriptSource\022\021\n\tfil" +
      "e_name\030\001 \001(\t\022\023\n\013method_name\030\002 \001(\t\022%\n\005lin" +
      "es\030\003 \003(\0132\026.data.ScriptSourceLine\"C\n\020Scri" +
      "ptSourceLine\022\014\n\004line\030\001 \001(\t\022\023\n\013line_numbe" +
      "r\030\002 \001(\005\022\014\n\004mark\030\003 \001(\010\"\253\002\n\004File\022\n\n\002id\030\001 \001" +
      "(\t\022\014\n\004size\030\002 \001(\003\022\020\n\010mimetype\030\003 \001(\t\022\025\n\rre" +
      "lative_path\030\004 \001(\t\022\031\n\021created_timestamp\030\005" +
      " \001(\003\022\025\n\rsha1_checksum\030\006 \001(\014\022\"\n\004meta\030\007 \003(" +
      "\0132\024.data.File.MetaEntry\022\025\n\rlast_modified" +
      "\030\t \001(\003\022\022\n\nproject_id\030\n \001(\t\022\016\n\006job_id\030\013 \001" +
      "(\t\022\024\n\014is_directory\030\014 \001(\010\022\014\n\004name\030\r \001(\t\032+" +
      "\n\tMetaEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:" +
      "\0028\001*W\n\023PClickPathEventType\022\013\n\007NOT_SET\020\000\022" +
      "\n\n\006WINDOW\020\001\022\t\n\005CLICK\020\002\022\t\n\005VALUE\020\003\022\010\n\004PAG" +
      "E\020\004\022\007\n\003URL\020\005*X\n\017PLogMessageType\022\013\n\007LMT_O" +
      "FF\020\000\022\r\n\tLMT_ERROR\020\001\022\014\n\010LMT_WARN\020\002\022\014\n\010LMT" +
      "_INFO\020\003\022\r\n\tLMT_DEBUG\020\004*C\n\024FailureCorrido" +
      "rValue\022\017\n\013FCV_NOT_SET\020\000\022\010\n\004HIGH\020\001\022\007\n\003MID" +
      "\020\002\022\007\n\003LOW\020\003*G\n\nMethodType\022\016\n\nMT_NOT_SET\020" +
      "\000\022\017\n\013TEST_METHOD\020\001\022\030\n\024CONFIGURATION_METH" +
      "OD\020\002*\252\001\n\016ExecStatusType\022\017\n\013EST_NOT_SET\020\000" +
      "\022\007\n\003NEW\020\001\022\013\n\007PENDING\020\002\022\020\n\014PROVISIONING\020\003" +
      "\022\013\n\007RUNNING\020\004\022\014\n\010FINISHED\020\005\022\013\n\007ABORTED\020\006" +
      "\022\013\n\007CRASHED\020\007\022\013\n\007INVALID\020\010\022\010\n\004VOID\020\t\022\023\n\017" +
      "ARTIFACT_UPLOAD\020\n*\307\001\n\020ResultStatusType\022\017" +
      "\n\013RST_NOT_SET\020\000\022\n\n\006NO_RUN\020\001\022\010\n\004INFO\020\002\022\013\n" +
      "\007SKIPPED\020\003\022\n\n\006PASSED\020\004\022\t\n\005MINOR\020\005\022\n\n\006FAI" +
      "LED\020\007\022\020\n\014FAILED_MINOR\020\010\022\022\n\016FAILED_RETRIE" +
      "D\020\t\022\023\n\017FAILED_EXPECTED\020\n\022\020\n\014PASSED_RETRY" +
      "\020\013\022\017\n\013MINOR_RETRY\020\014B2\n.eu.tsystems.mms.t" +
      "ic.testframework.report.modelP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_data_SuiteContext_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_data_SuiteContext_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_SuiteContext_descriptor,
        new java.lang.String[] { "ContextValues", "TestContextIds", "ExecutionContextId", });
    internal_static_data_ClassContext_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_data_ClassContext_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_ClassContext_descriptor,
        new java.lang.String[] { "ContextValues", "MethodContextIds", "FullClassName", "SimpleClassName", "TestContextId", "ExecutionContextId", "TestContextName", "Merged", });
    internal_static_data_TestContext_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_data_TestContext_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_TestContext_descriptor,
        new java.lang.String[] { "ContextValues", "ClassContextIds", "SuiteContextId", "ExecutionContextId", });
    internal_static_data_ExecutionContext_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_data_ExecutionContext_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_ExecutionContext_descriptor,
        new java.lang.String[] { "ContextValues", "SuiteContextIds", "RunConfig", "ProjectId", "JobId", "RunId", "TaskId", "ExclusiveSessionContextIds", "EstimatedTestMethodCount", "LogMessages", });
    internal_static_data_MethodContext_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_data_MethodContext_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_MethodContext_descriptor,
        new java.lang.String[] { "ContextValues", "MethodType", "Parameters", "MethodTags", "RetryNumber", "MethodRunIndex", "ThreadName", "FailureCorridorValue", "ClassContextId", "ExecutionContextId", "NonFunctionalInfos", "CollectedAssertions", "Infos", "PriorityMessage", "RelatedMethodContextIds", "DependsOnMethodContextIds", "ErrorContext", "TestSteps", "TestContextId", "SuiteContextId", "SessionContextIds", "VideoIds", "CustomContextJson", });
    internal_static_data_ContextValues_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_data_ContextValues_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_ContextValues_descriptor,
        new java.lang.String[] { "Id", "Created", "Name", "StartTime", "EndTime", "Swi", "ResultStatus", "ExecStatus", });
    internal_static_data_PTestStep_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_data_PTestStep_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_PTestStep_descriptor,
        new java.lang.String[] { "Name", "TestStepActions", });
    internal_static_data_PTestStepAction_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_data_PTestStepAction_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_PTestStepAction_descriptor,
        new java.lang.String[] { "Name", "Timestamp", "ClickpathEvents", "ScreenshotIds", "LogMessages", });
    internal_static_data_PClickPathEvent_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_data_PClickPathEvent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_PClickPathEvent_descriptor,
        new java.lang.String[] { "Type", "Subject", "SessionId", });
    internal_static_data_PLogMessage_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_data_PLogMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_PLogMessage_descriptor,
        new java.lang.String[] { "Type", "LoggerName", "Message", "Timestamp", });
    internal_static_data_ErrorContext_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_data_ErrorContext_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_ErrorContext_descriptor,
        new java.lang.String[] { "ScriptSource", "ExecutionObjectSource", "TicketId", "Description", "Cause", });
    internal_static_data_SessionContext_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_data_SessionContext_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_SessionContext_descriptor,
        new java.lang.String[] { "ContextValues", "SessionKey", "Provider", "Metadata", "SessionId", });
    internal_static_data_SessionContext_MetadataEntry_descriptor =
      internal_static_data_SessionContext_descriptor.getNestedTypes().get(0);
    internal_static_data_SessionContext_MetadataEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_SessionContext_MetadataEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_data_RunConfig_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_data_RunConfig_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_RunConfig_descriptor,
        new java.lang.String[] { "Runcfg", "BuildInformation", "ReportName", });
    internal_static_data_BuildInformation_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_data_BuildInformation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_BuildInformation_descriptor,
        new java.lang.String[] { "BuildJavaVersion", "BuildOsName", "BuildOsVersion", "BuildUserName", "BuildVersion", "BuildTimestamp", });
    internal_static_data_StackTraceCause_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_data_StackTraceCause_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_StackTraceCause_descriptor,
        new java.lang.String[] { "ClassName", "Message", "StackTraceElements", "Cause", });
    internal_static_data_ScriptSource_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_data_ScriptSource_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_ScriptSource_descriptor,
        new java.lang.String[] { "FileName", "MethodName", "Lines", });
    internal_static_data_ScriptSourceLine_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_data_ScriptSourceLine_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_ScriptSourceLine_descriptor,
        new java.lang.String[] { "Line", "LineNumber", "Mark", });
    internal_static_data_File_descriptor =
      getDescriptor().getMessageTypes().get(17);
    internal_static_data_File_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_File_descriptor,
        new java.lang.String[] { "Id", "Size", "Mimetype", "RelativePath", "CreatedTimestamp", "Sha1Checksum", "Meta", "LastModified", "ProjectId", "JobId", "IsDirectory", "Name", });
    internal_static_data_File_MetaEntry_descriptor =
      internal_static_data_File_descriptor.getNestedTypes().get(0);
    internal_static_data_File_MetaEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_File_MetaEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
