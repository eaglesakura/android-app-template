package replace.your.app_package.gen.prop;


public class ExampleProps extends com.eaglesakura.sloth.db.property.internal.GeneratedProperties {
    
    public static final String ID_BOOLVALUE = "ExampleProps.boolValue";
    public static final String ID_INTVALUE = "ExampleProps.intValue";
    public static final String ID_DOUBLEVALUE = "ExampleProps.doubleValue";
    public static final String ID_STRINGVALUE = "ExampleProps.stringValue";
    public static final String ID_BYTEARRAYVALUE = "ExampleProps.byteArrayValue";
    
    public ExampleProps(){ }
    public ExampleProps(com.eaglesakura.sloth.db.property.PropertyStore store){ setPropertyStore(store); }
    public void setBoolValue(boolean set){ setProperty("ExampleProps.boolValue", set); }
    public boolean isBoolValue(){ return getBooleanProperty("ExampleProps.boolValue"); }
    public void setIntValue(int set){ setProperty("ExampleProps.intValue", set); }
    public int getIntValue(){ return getIntProperty("ExampleProps.intValue"); }
    public void setDoubleValue(double set){ setProperty("ExampleProps.doubleValue", set); }
    public double getDoubleValue(){ return getDoubleProperty("ExampleProps.doubleValue"); }
    public void setStringValue(String set){ setProperty("ExampleProps.stringValue", set); }
    public String getStringValue(){ return getStringProperty("ExampleProps.stringValue"); }
    public void setByteArrayValue(byte[] set){ setProperty("ExampleProps.byteArrayValue", set); }
    public byte[] getByteArrayValue(){ return getByteArrayProperty("ExampleProps.byteArrayValue"); }
    
}
