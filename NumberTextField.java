package application;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField
{	
	public NumberTextField()
	{
		this.setPromptText("Enter");
	}

	/*
	 * (non-Javadoc)
	 * @see javafx.scene.control.TextInputControl#replaceText(int, int, java.lang.String)
	 * v1.1 edit:
	 * -limit size of strings accepted
	 */
	
	@Override
	public void replaceText(int start, int end, String text)
	{
		if (text.matches("[0-9]")|| text.isEmpty() || text.matches("[.]"))
		{
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String replacement)
	{
		super.replaceSelection(replacement);
	}
	
	public void limitTextCharacters()
	{
		
	}

}
