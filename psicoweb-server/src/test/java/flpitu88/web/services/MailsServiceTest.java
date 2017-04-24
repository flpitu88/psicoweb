package flpitu88.web.services;

import flpitu88.web.backend.psicoweb.services.MailsService;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
@RunWith(MockitoJUnitRunner.class)
public class MailsServiceTest {
    
     @Mock
    private Environment env;

    @InjectMocks
    private MailsService mailsService;
    
    @Test
    @Ignore
    public void mandarMailTest(){
        when(env.getProperty("direccionMail"))
                .thenReturn("flavio.pietrolati@gmail.com");
        when(env.getProperty("clave"))
                .thenReturn("soypitu1188jls");
        
        mailsService.enviarMail();
        
        Assert.assertTrue(true);
    }
    
}
