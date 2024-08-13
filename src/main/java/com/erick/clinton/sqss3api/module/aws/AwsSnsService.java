package com.erick.clinton.sqss3api.module.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.erick.clinton.sqss3api.module.aws.dto.MessageDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    private final AmazonSNS amazonSNS;
    private final Topic catalogTopic;

    public AwsSnsService(AmazonSNS amazonSNS, @Qualifier("catalogEventsTopic") Topic catalogTopic){
        this.amazonSNS = amazonSNS;
        this.catalogTopic = catalogTopic;
    }

    public void publish(MessageDto messageDto){
        this.amazonSNS.publish(this.catalogTopic.getTopicArn(),messageDto.toString());
    }
}
