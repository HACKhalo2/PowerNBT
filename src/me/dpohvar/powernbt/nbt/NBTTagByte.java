package me.dpohvar.powernbt.nbt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static me.dpohvar.powernbt.utils.StaticValues.classNBTTagByte;
import static me.dpohvar.powernbt.utils.VersionFix.getNew;

/**
 * 14.01.13 17:54
 *
 * @author DPOH-VAR
 */
public class NBTTagByte extends NBTTagNumeric {
    private static Class clazz = classNBTTagByte;
    private static Class[] classes = new Class[]{String.class, byte.class};
    private static Field fieldData;
    private static Method methodRead;
    private static Method methodWrite;
    private static Method methodClone;

    static {
        try {
            methodRead = clazz.getDeclaredMethod("load", java.io.DataInput.class);
            methodWrite = clazz.getDeclaredMethod("write", java.io.DataOutput.class);
            methodClone = clazz.getDeclaredMethod("clone");
            fieldData = clazz.getDeclaredField("data");
            methodRead.setAccessible(true);
            methodWrite.setAccessible(true);
            methodClone.setAccessible(true);
            fieldData.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NBTTagByte() {
        this("", (byte) 0);
    }

    public NBTTagByte(String s) {
        this(s, (byte) 0);
    }

    public NBTTagByte(byte b) {
        this("", b);
    }

    public NBTTagByte(String s, byte b) {
        super(getNew(clazz, classes, s, b));
    }

    public NBTTagByte(boolean ignored, Object tag) {
        super(tag);
        if (!clazz.isInstance(tag)) throw new IllegalArgumentException();
    }

    public void write(java.io.DataOutput output) {
        try {
            methodWrite.invoke(handle, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(java.io.DataInput input) {
        try {
            methodRead.invoke(handle, input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NBTTagByte clone() {
        try {
            Object h = methodClone.invoke(handle);
            return new NBTTagByte(true, h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean equals(Object o) {
        if (o instanceof NBTBase) o = ((NBTBase) o).getHandle();
        return handle.equals(o);
    }

    public int hashCode() {
        return handle.hashCode();
    }

    public Byte get() {
        try {
            return (Byte) fieldData.get(handle);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(Number value) {
        try {
            fieldData.set(handle, value.byteValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte getTypeId() {
        return 1;
    }

}
