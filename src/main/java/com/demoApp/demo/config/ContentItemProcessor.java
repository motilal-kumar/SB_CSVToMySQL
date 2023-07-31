package com.demoApp.demo.config;

import com.demoApp.demo.entity.Content;
import org.springframework.batch.item.ItemProcessor;

public class ContentItemProcessor implements ItemProcessor<Content, Content> {

    @Override
    public Content process(Content content) throws Exception {

        return content;
    }
}
