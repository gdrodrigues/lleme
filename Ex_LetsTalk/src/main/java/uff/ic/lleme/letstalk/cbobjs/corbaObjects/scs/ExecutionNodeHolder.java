package uff.ic.lleme.letstalk.cbobjs.corbaObjects.scs;

/**
 * corbaObjects/scs/ExecutionNodeHolder.java . Generated by the IDL-to-Java
 * compiler (portable), version "3.2" from deployment.idl Sexta-feira, 9 de
 * Dezembro de 2005 18h26min28s BRST
 */

public final class ExecutionNodeHolder implements
		org.omg.CORBA.portable.Streamable {
	public uff.ic.lleme.letstalk.cbobjs.corbaObjects.scs.ExecutionNode value = null;

	public ExecutionNodeHolder() {
	}

	public ExecutionNodeHolder(uff.ic.lleme.letstalk.cbobjs.corbaObjects.scs.ExecutionNode initialValue) {
		value = initialValue;
	}

	public void _read(org.omg.CORBA.portable.InputStream i) {
		value = uff.ic.lleme.letstalk.cbobjs.corbaObjects.scs.ExecutionNodeHelper.read(i);
	}

	public void _write(org.omg.CORBA.portable.OutputStream o) {
		uff.ic.lleme.letstalk.cbobjs.corbaObjects.scs.ExecutionNodeHelper.write(o, value);
	}

	public org.omg.CORBA.TypeCode _type() {
		return uff.ic.lleme.letstalk.cbobjs.corbaObjects.scs.ExecutionNodeHelper.type();
	}

}