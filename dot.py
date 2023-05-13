import os
import pydot

# Especifica la ruta de la carpeta con los archivos .dot
dot_folder_path = 'searchGVTrees'

# Especifica la ruta de la carpeta donde se guardarán los archivos .jpg
jpg_folder_path = 'fotosArboles'


# Para cada archivo .dot en la carpeta
for filename in os.listdir(dot_folder_path):
    if filename.endswith('.dot'):
        # Construir las rutas completas del archivo .dot y el archivo .jpg de salida
        dot_path = os.path.join(dot_folder_path, filename)
        jpg_path = os.path.join(jpg_folder_path, os.path.splitext(filename)[0] + '.png')    
        (graph,) = pydot.graph_from_dot_file(dot_path) 
        graph.write_png(jpg_path)


print('Proceso finalizado')
