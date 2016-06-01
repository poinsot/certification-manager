tinymce.init({ 
	selector:'#textQuestion', 
	width : '50%', 
	height : '200',
	menu: {
		    edit: {title: 'Edit', items: 'undo redo | cut copy paste pastetext | selectall'},
		    view: {title: 'View', items: 'visualaid'},
		    format: {title: 'Format', items: 'bold italic underline strikethrough superscript subscript',block_formats: 'Paragraph=p;Header 1=h1;Header 2=h2;Header 3=h3'},
		    table: {title: 'Table', items: 'inserttable tableprops deletetable | cell row column'}
}});