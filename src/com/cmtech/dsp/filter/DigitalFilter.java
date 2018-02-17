package com.cmtech.dsp.filter;

import com.cmtech.dsp.filter.structure.FSType;
import com.cmtech.dsp.filter.structure.IDFStructure;
import com.cmtech.dsp.seq.RealSeq;

public abstract class DigitalFilter extends AbstractFilter implements IDigitalFilter{
	protected IDFStructure structure;
	
	public DigitalFilter(double[] b, double[] a) {
		super(b, a);
	}

	public DigitalFilter(RealSeq bseq, RealSeq aseq){
		super(bseq, aseq);
	}

	public double filter(double x) {
		return structure.filter(x);
	}
	
	public abstract void createStructure(FSType sType);
	
	@Override
	public String toString() {
		if(structure == null) 
			return super.toString();
		else
			return super.toString() + '\n' + structure.toString();
	}
}
