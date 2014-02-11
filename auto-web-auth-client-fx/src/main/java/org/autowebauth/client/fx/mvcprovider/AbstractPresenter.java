package org.autowebauth.client.fx.mvcprovider;

public class AbstractPresenter
{
   private AbstractView view;

   public AbstractView getView()
   {
      return this.view;
   }

   void setView(AbstractView v)
   {
      this.view = v;
   }

}
