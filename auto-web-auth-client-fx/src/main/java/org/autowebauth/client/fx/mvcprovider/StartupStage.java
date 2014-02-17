package org.autowebauth.client.fx.mvcprovider;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Target({ TYPE, METHOD, PARAMETER  })
@Retention(RUNTIME)
@Qualifier
public @interface StartupStage
{
   
}
