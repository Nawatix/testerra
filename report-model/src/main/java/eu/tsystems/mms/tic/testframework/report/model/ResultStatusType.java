// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: framework.proto

package eu.tsystems.mms.tic.testframework.report.model;

/**
 * <pre>
 * All status elements for an actual executed test method
 * </pre>
 *
 * Protobuf enum {@code data.ResultStatusType}
 */
public enum ResultStatusType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>RST_NOT_SET = 0;</code>
   */
  RST_NOT_SET(0),
  /**
   * <pre>
   * default status when entity is spawned, basically an illegal end status
   * </pre>
   *
   * <code>NO_RUN = 1;</code>
   */
  NO_RUN(1),
  /**
   * <pre>
   * info status, not representative
   * </pre>
   *
   * <code>INFO = 2;</code>
   */
  INFO(2),
  /**
   * <pre>
   * skipped
   * </pre>
   *
   * <code>SKIPPED = 3;</code>
   */
  SKIPPED(3),
  /**
   * <pre>
   * passed status, without any issue
   * </pre>
   *
   * <code>PASSED = 4;</code>
   */
  PASSED(4),
  /**
   * <pre>
   * passed, but with minor, non-blocking issues
   * </pre>
   *
   * <code>MINOR = 5;</code>
   */
  MINOR(5),
  /**
   * <pre>
   * failed status, with hard failing issues
   * </pre>
   *
   * <code>FAILED = 7;</code>
   */
  FAILED(7),
  /**
   * <pre>
   * failed with additional minor issues
   * </pre>
   *
   * <code>FAILED_MINOR = 8;</code>
   */
  FAILED_MINOR(8),
  /**
   * <pre>
   * failed and retry is triggered
   * </pre>
   *
   * <code>FAILED_RETRIED = 9;</code>
   */
  FAILED_RETRIED(9),
  /**
   * <pre>
   * entity was expected to fail (with or without an explicit issue), it may or may not be representative
   * </pre>
   *
   * <code>FAILED_EXPECTED = 10;</code>
   */
  FAILED_EXPECTED(10),
  /**
   * <pre>
   * passed after a retry
   * </pre>
   *
   * <code>PASSED_RETRY = 11;</code>
   */
  PASSED_RETRY(11),
  /**
   * <pre>
   * minor after a retry
   * </pre>
   *
   * <code>MINOR_RETRY = 12;</code>
   */
  MINOR_RETRY(12),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>RST_NOT_SET = 0;</code>
   */
  public static final int RST_NOT_SET_VALUE = 0;
  /**
   * <pre>
   * default status when entity is spawned, basically an illegal end status
   * </pre>
   *
   * <code>NO_RUN = 1;</code>
   */
  public static final int NO_RUN_VALUE = 1;
  /**
   * <pre>
   * info status, not representative
   * </pre>
   *
   * <code>INFO = 2;</code>
   */
  public static final int INFO_VALUE = 2;
  /**
   * <pre>
   * skipped
   * </pre>
   *
   * <code>SKIPPED = 3;</code>
   */
  public static final int SKIPPED_VALUE = 3;
  /**
   * <pre>
   * passed status, without any issue
   * </pre>
   *
   * <code>PASSED = 4;</code>
   */
  public static final int PASSED_VALUE = 4;
  /**
   * <pre>
   * passed, but with minor, non-blocking issues
   * </pre>
   *
   * <code>MINOR = 5;</code>
   */
  public static final int MINOR_VALUE = 5;
  /**
   * <pre>
   * failed status, with hard failing issues
   * </pre>
   *
   * <code>FAILED = 7;</code>
   */
  public static final int FAILED_VALUE = 7;
  /**
   * <pre>
   * failed with additional minor issues
   * </pre>
   *
   * <code>FAILED_MINOR = 8;</code>
   */
  public static final int FAILED_MINOR_VALUE = 8;
  /**
   * <pre>
   * failed and retry is triggered
   * </pre>
   *
   * <code>FAILED_RETRIED = 9;</code>
   */
  public static final int FAILED_RETRIED_VALUE = 9;
  /**
   * <pre>
   * entity was expected to fail (with or without an explicit issue), it may or may not be representative
   * </pre>
   *
   * <code>FAILED_EXPECTED = 10;</code>
   */
  public static final int FAILED_EXPECTED_VALUE = 10;
  /**
   * <pre>
   * passed after a retry
   * </pre>
   *
   * <code>PASSED_RETRY = 11;</code>
   */
  public static final int PASSED_RETRY_VALUE = 11;
  /**
   * <pre>
   * minor after a retry
   * </pre>
   *
   * <code>MINOR_RETRY = 12;</code>
   */
  public static final int MINOR_RETRY_VALUE = 12;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static ResultStatusType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static ResultStatusType forNumber(int value) {
    switch (value) {
      case 0: return RST_NOT_SET;
      case 1: return NO_RUN;
      case 2: return INFO;
      case 3: return SKIPPED;
      case 4: return PASSED;
      case 5: return MINOR;
      case 7: return FAILED;
      case 8: return FAILED_MINOR;
      case 9: return FAILED_RETRIED;
      case 10: return FAILED_EXPECTED;
      case 11: return PASSED_RETRY;
      case 12: return MINOR_RETRY;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ResultStatusType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      ResultStatusType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ResultStatusType>() {
          public ResultStatusType findValueByNumber(int number) {
            return ResultStatusType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return eu.tsystems.mms.tic.testframework.report.model.Framework.getDescriptor().getEnumTypes().get(4);
  }

  private static final ResultStatusType[] VALUES = values();

  public static ResultStatusType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private ResultStatusType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:data.ResultStatusType)
}
