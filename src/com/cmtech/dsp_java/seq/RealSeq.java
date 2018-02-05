package com.cmtech.dsp_java.seq;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class RealSeq implements IRealSeq {
	private double[] data;
	
	public RealSeq() {
		data = new double[0];
	}
	
	public RealSeq(int N) {
		initToZeroSequence(N);
	}
	
	public RealSeq(double...d) {
		data = Arrays.copyOf(d, d.length);
	}
	
	
	public RealSeq(Collection<Double> d) {
		data = new double[d.size()];
		int i = 0;
		for(Double ele : d) {
			data[i] = ele;
		}
	}
	
	public RealSeq(RealSeq seq) {
		data = Arrays.copyOf(seq.data, seq.size());
	}

	@Override
	public void initToZeroSequence(int N) {
		data = new double[N];
	}	

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.cmtech.dsp_java.seq.ISeq#clear()
	 */
	@Override
	public void clear() {
		data = new double[0];
	}
	
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.cmtech.dsp_java.seq.ISeq#size()
	 */
	@Override
	public int size() {
		return data.length;
	}

	@Override
	public void changeSize(int N) {
		if(size() == N) return;
		
		double[] buf = new double[N];
		for(int i = 0; i < N; i++) {
			if(i < data.length) buf[i] = data[i];
			else buf[i] = 0.0;
		}
		data = buf;
	}
	
	
	@Override
	public RealSeq abs() {
		RealSeq out = new RealSeq(this);
		for(int i = 0; i < size(); i++) {
			out.data[i] = Math.abs(out.data[i]);
		}
		return out;
	}
	
	@Override
	public RealSeq angle() {
		RealSeq out = new RealSeq(size());
		for(int i = 0; i < size(); i++) {
			out.data[i] = Math.atan2(0, data[i]);
		}
		return out;
	}
	
	@Override
	public ComplexSeq dtft(RealSeq omega) {
		ComplexSeq out = new ComplexSeq(this).dtft(omega);
		return out;
	}
	
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.cmtech.dsp_java.seq.ISeq#dtft(int)
	 */
	@Override
	public ComplexSeq dtft(int N) {
		return dtft(SeqFactory.linSpace(0, Math.PI, N));
	}
	
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.cmtech.dsp_java.seq.IRealSeq#get(int)
	 */
	@Override
	public double get(int i) {
		// TODO Auto-generated method stub
		return data[i];
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.cmtech.dsp_java.seq.IRealSeq#set(int, double)
	 */
	@Override
	public boolean set(int i, double element) {
		data[i] = element;
		return true;
	}
	
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.cmtech.dsp_java.seq.IRealSeq#toArray()
	 */
	@Override
	public double[] toArray() {
		// TODO Auto-generated method stub
		return Arrays.copyOf(data, data.length);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[ size=" + data.length + " data=" + Arrays.toString(data) + " ]";
	}
	
	@Override
	public RealSeq reverse() {
		RealSeq out = new RealSeq(size());
		for(int i = 0; i < data.length; i++)
	    {
	        out.data[i] = data[data.length-1-i];
	    }
		return out;
	}
	
	@Override
	public RealSeq plus(double a) {
		RealSeq out = new RealSeq(this);
		for(int i = 0; i < size(); i++) {
			out.data[i] += a;
		}
		return out;
	}
	
	@Override
	public RealSeq minus(double a) {
		RealSeq out = new RealSeq(this);
		for(int i = 0; i < size(); i++) {
			out.data[i] -= a;
		}
		return out;
	}

	@Override
	public RealSeq multiple(double a) {
		RealSeq out = new RealSeq(this);
		for(int i = 0; i < size(); i++) {
			out.data[i] *= a;
		}
		return out;
	}	
	
	@Override
	public RealSeq divide(double a) {
		RealSeq out = new RealSeq(this);
		for(int i = 0; i < size(); i++) {
			out.data[i] /= a;
		}
		return out;
	}	
	


	@Override
	public double sum() {
		double sum = 0.0;
		for(double ele : data) {
			sum += ele;
		}
		
		return sum;
	}

	@Override
	public double max() {
		if(data.length < 1) return 0.0;
		double tmp = data[0];
		for(int i = 1; i < data.length; i++) {
			tmp = (tmp < data[i]) ? data[i] : tmp;
		}
		return tmp;
	}
	
	@Override
	public double min() {
		if(data.length < 1) return 0.0;
		double tmp = data[0];
		for(int i = 1; i < data.length; i++) {
			tmp = (tmp > data[i]) ? data[i] : tmp;
		}
		return tmp;
	}

	@Override
	public ComplexSeq fft() {
		FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
		org.apache.commons.math3.complex.Complex[] result = fft.transform(data, TransformType.FORWARD); 
		ComplexSeq rtn = new ComplexSeq(result.length);
		for(int i = 0; i < result.length; i++) {
			rtn.set(i, new Complex(result[i].getReal(), result[i].getImaginary()));
		}
		return rtn;
	}
	
	

}
