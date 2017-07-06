package gnews.acamaroti.aferreira.basicas;

import java.io.Serializable;

 public class Noticia implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	    public long id;
		public String titulo;
		public String link;
		public String data;
		public boolean favorito;
		public String descricao;

		public Noticia(String titulo, String data,
				String link, String descricao) {
			this.titulo = titulo;
			this.data = data;
			this.link = link;
			this.descricao = descricao;
		}

		
		
		
		
		public Noticia() {
			super();
		}





		//Usado para o banco pois precisa do ID
		public Noticia(long id, String titulo, String link, String data, String descricao) {
			this(titulo,data, link,descricao);
			this.id = id;
		}




		@Override
		public String toString() {
			return titulo;
		}
	}
