package dcpb.re.accountable.enc;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;
import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;

public class PubParams {
	Pairing pairing;
	Field G1,G2,GT,Zr;
	CurveElement g,h,p_pub;
	ZrElement one;
	//MSK;
	public PubParams() {
		//one=(ZrElement) this.Zr.newOneElement();
		// TODO Auto-generated constructor stub
	}
	public Element hash(Element e) {
		return hashFromBytesToZp(e.toBytes());
	}
    //H : {0, 1}* -> Zp
    public  Element hashFromStringToZp(String str) {
    
        return this.Zr.newElement().setFromHash(str.getBytes(), 0, str.length()).getImmutable();
    }
 
    public Element hashFromBytesToZp( byte[] bytes) {
        return this.Zr.newElement().setFromHash(bytes, 0, bytes.length).getImmutable();
    }
    public Element permutationFunction(Element m) {
    	return m;
    }
    public byte[] xor(byte[] as,byte[] bs) {

    	if(as.length<bs.length) {
    		byte[] tmp=as;
    		as=bs;
    		bs=tmp;
    	}
    	byte[] ret=new byte[as.length];
    	int begin=as.length-bs.length;
    	for(int i=0;i<begin;i++) {
    		ret[i]=as[i];
    	}
    	for(int i=begin;i<as.length;i++) {
    		ret[i]=(byte) (as[i]^bs[i-begin]);
    	}
    	return ret;
    }
   

}
