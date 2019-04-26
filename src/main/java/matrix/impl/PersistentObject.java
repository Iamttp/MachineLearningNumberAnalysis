package matrix.impl;

import java.io.*;

public abstract class PersistentObject implements Serializable, Cloneable {
    public static final long serialVersionUID = 1020L;

    protected PersistentObject() {
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException var2) {
            throw new InternalError();
        }
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        //将对象写入流中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this);
        //从流中取出
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.readObject();

    }
}
