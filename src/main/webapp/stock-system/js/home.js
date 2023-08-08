$(document).ready(function() {
	      var contentDiv = $('.content');

	     $('#home').click(function() {
		    $.ajax({
		        url: 'home.jsp',
		        dataType: 'html',
		        success: function(data) {
		            $('body').html(data);
		        }
		    });
		});
	
	      $('#products').click(function() {
		        $.ajax({
		          url: 'tables/products.jsp',
		          dataType: 'html',
		          success: function(data) {
		            contentDiv.html(data);
		          }
		        });
		      });
	
	      $('#orders').click(function() {
		        $.ajax({
		          url: 'tables/orders.jsp',
		          dataType: 'html',
		          success: function(data) {
		            contentDiv.html(data);
		          }
		        });
		      });
	
	      $('#users').click(function() {
		        $.ajax({
		          url: 'tables/users.jsp',
		          dataType: 'html',
		          success: function(data) {
		            contentDiv.html(data);
		          }
		        });
		        
		      });
	      
	      $('#supply-chain').click(function() {
		        $.ajax({
		          url: 'tables/supply-chain.jsp',
		          dataType: 'html',
		          success: function(data) {
		            contentDiv.html(data);
		          }
		        });
		      });
		     
	      
	    });