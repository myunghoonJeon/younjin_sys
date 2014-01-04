package org.youngjin.net.invoice;

import org.youngjin.net.util.AbstractListFilter;

public class InvoiceFilter extends AbstractListFilter {

	private String process;

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@Override
	public String toString() {
		return "InvoiceFilter [process=" + process + "]";
	}

}
