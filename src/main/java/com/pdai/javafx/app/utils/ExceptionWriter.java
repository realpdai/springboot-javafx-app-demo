package com.pdai.javafx.app.utils;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * <b>ClassName</b>: ExceptionWriter <br/>
 *
 * <b>Description</b>: TODO ADD FUNCTION. <br/>
 *
 * <b>Date</b>: Apr 22, 2019 1:11:29 PM <br/>
 * 
 * @author peng.dai@siemens.com
 * @version Apr 22, 2019
 *
 */
public class ExceptionWriter extends PrintWriter {
	public ExceptionWriter(Writer writer) {
		super(writer);
	}

	private String wrapAroundWithNewlines(String stringWithoutNewlines) {
		return ("\n" + stringWithoutNewlines + "\n");
	}

	/*
	 * Convert a stacktrace into a string
	 */
	public String getExceptionAsString(Throwable throwable) {
		throwable.printStackTrace(this);

		String exception = super.out.toString();

		return (wrapAroundWithNewlines(exception));
	}
}
