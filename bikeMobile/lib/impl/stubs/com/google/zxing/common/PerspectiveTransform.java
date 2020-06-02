package com.google.zxing.common;


/**
 *  <p>This class implements a perspective transform in two dimensions. Given four source and four
 *  destination points, it will compute the transformation implied between them. The code is based
 *  directly upon section 3.4.2 of George Wolberg's "Digital Image Warping"; see pages 54-56.</p>
 * 
 *  @author Sean Owen
 */
public final class PerspectiveTransform {

	public static PerspectiveTransform quadrilateralToQuadrilateral(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3, float x0p, float y0p, float x1p, float y1p, float x2p, float y2p, float x3p, float y3p) {
	}

	public void transformPoints(float[] points) {
	}

	public void transformPoints(float[] xValues, float[] yValues) {
	}

	public static PerspectiveTransform squareToQuadrilateral(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
	}

	public static PerspectiveTransform quadrilateralToSquare(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
	}
}
