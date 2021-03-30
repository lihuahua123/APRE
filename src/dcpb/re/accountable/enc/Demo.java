package dcpb.re.accountable.enc;


import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;
import it.unisa.dia.gas.plaf.jpbc.field.z.ZrElement;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;


public class Demo {


	public static void main(String[] args) {


		PubParams p=new PubParams();
		p.pairing = PairingFactory.getPairing("resource/a.properties");
		/* Return G1 */
		p.G1 = p.pairing.getG1();

		/* Return G2 */
		p.G2 = p.pairing.getG2();

		/* Return GT */
		p.GT = p.pairing.getGT();
		/* Return Zr */
		p.Zr = p.pairing.getZr();
		p.one=(ZrElement) p.Zr.newOneElement();
		p.g = (CurveElement) p.G1.newRandomElement().getImmutable();
		p.h = (CurveElement) p.G2.newRandomElement().getImmutable();

		Element one=p.Zr.newOneElement();

		Element g1=p.G1.newRandomElement();
		Element g2=p.G1.newRandomElement();
		Element h1=p.G1.newRandomElement();
		Element h2=p.G1.newRandomElement();
		Element L=p.pairing.pairing(h1,h2);

		Element z=p.Zr.newRandomElement();
		Element Z=g2.duplicate().powZn(z);

		Element xalice=p.Zr.newRandomElement();
		Element yalice=p.Zr.newRandomElement();
		Element Xalice=h1.duplicate().powZn(xalice);
		Element Yalice=g1.duplicate().powZn(yalice);


		Element xbob=p.Zr.newRandomElement();
		Element ybob=p.Zr.newRandomElement();
		Element Xbob=h1.duplicate().powZn(xbob);
		Element Ybob=g1.duplicate().powZn(ybob);
		long startTime = System.currentTimeMillis();    //获取开始时间
		Element W=h2.duplicate().mul(Ybob).mul(Z).powZn(one.duplicate().div(xalice));

		Element r=p.Zr.newRandomElement();

		Element m=p.GT.newRandomElement();//p.G1.newElementFromBytes(message.toByteArray());
		System.out.println(m);
//		Element c01=L.duplicate().powZn(r).mul(m);
//		Element c11=g1.duplicate().powZn(r);
//		Element c21=L.duplicate().powZn(r).mul(p.pairing.pairing(h1,Ybob).powZn(r));

		Element c0=L.duplicate().powZn(r).mul(m);
		Element c1=g1.duplicate().powZn(r);
		Element c2=p.pairing.pairing(h1,g2).powZn(r);
		Element c3=Xalice.duplicate().powZn(r);

		Element c01=c0;
		Element c11=c1;
		Element c21=p.pairing.pairing(c3,W).div(c2.duplicate().powZn(z));
		Element aaa=L.duplicate().powZn(r);


		Element m1=c01.duplicate().mul(p.pairing.pairing(h1,c11).powZn(ybob)).div(c21);


		Element m2=c0.duplicate().div(p.pairing.pairing(c3,h2).powZn(one.div(xalice)));
		long endTime = System.currentTimeMillis();    //获取结束时间
		//System.out.println("读写程序运行时间：" + time2 + "ms");    //输出程序运行时间
		System.out.print( (endTime - startTime)+",");    //输出程序运行时间

		

	}

	
}