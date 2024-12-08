package org.example.java.tutorial.java22;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringTemplateSecondPreview {

    void main() {

        String name = "Alice";
        String greeting = STR."Hello, ${name}!";
        log.info(greeting); // Outputs: Hello, Alice! , Well doesn't work for me

        name = "Bob";
        int age = 30;
        String message = STR."User: ${name}, Age: ${age + 1}";
        log.info(message); // Outputs: User: Bob, Age: 31

        // Usage HtmlProcessor
        var HTML = new HtmlProcessor();
        String html = HTML."Hello, ${name}!";
        log.info(html); // Outputs: <html>Hello, Bob!</html>
    }
}

class HtmlProcessor implements StringTemplate.Processor<String, RuntimeException> {
    @Override
    public String process(StringTemplate st) {
        return "<html>" + st.interpolate() + "</html>";
    }
}
