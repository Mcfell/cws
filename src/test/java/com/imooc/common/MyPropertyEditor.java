package com.imooc.common;



import java.beans.PropertyEditorSupport;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ckc.cws.entity.User;

/**
 * Created by geely on 2015/11/22.
 */
public class MyPropertyEditor extends PropertyEditorSupport {
	private static Logger logger = LoggerFactory.getLogger(MyPropertyEditor.class); 
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        User u = new User();
        String[] textArray = text.split(",");
        u.setName(textArray[0]);
        u.setAge(Integer.parseInt(textArray[1]));
        this.setValue(u);
    }

    public static void main(String[] args) {
        MyPropertyEditor editor = new MyPropertyEditor();
        editor.setAsText("tom,22");
        logger.debug(editor.getValue().toString());
    }
}
