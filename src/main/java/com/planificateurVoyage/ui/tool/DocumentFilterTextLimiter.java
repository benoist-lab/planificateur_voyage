package com.planificateurVoyage.ui.tool;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentFilterTextLimiter extends DocumentFilter {
private int max;
 
public DocumentFilterTextLimiter(int max) {
this.max = max;
}
 
public void insertString(DocumentFilter.FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
 
replace(fb, offset, 0, str, attr);
}
 
public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet attrs) throws BadLocationException {
int newLength = fb.getDocument().getLength() - length + str.length();
 
if (newLength <= max) {
fb.replace(offset, length, str, attrs);
} else {
Toolkit.getDefaultToolkit().beep();
}
}
}
 

