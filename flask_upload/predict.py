import subprocess
import matplotlib
matplotlib.use('TkAgg')

import matplotlib as plt
import pathlib
import tensorflow as tf, sys
import numpy as np
import pandas

def predict(image_path, model_path):
    model_dir = model_path
    imagedir = image_path

    model_path = model_dir + "/retrained_graph.pb"
    labels_path = model_dir + "/retrained_labels.txt"
    batch_run = 1

    if (batch_run == 1):
        config = tf.ConfigProto()
        config.gpu_options.allow_growth=True
        sess = tf.Session(config=config)

        with tf.gfile.FastGFile(model_path, 'rb') as f:
            graph_def = tf.GraphDef()
            graph_def.ParseFromString(f.read())
            _ = tf.import_graph_def(graph_def, name='')

        with tf.Session() as sess:
            softmax_tensor = sess.graph.get_tensor_by_name('final_result:0')
            sub_dir =  [q for q in pathlib.Path(imagedir).iterdir() if q.is_dir()]

            result_summary = []
            labels = ['heart','oblong','oval','round','sqaure']
            for j in range(len(sub_dir)):
                images_dir = [p for p in pathlib.Path(sub_dir[j]).iterdir() if p.is_file()]
                for i in range(len(images_dir)):

                    image_path = str(images_dir[i])
                    image_data = tf.gfile.FastGFile(image_path, 'rb').read()
                    predictions = sess.run(softmax_tensor, \
                        {'DecodeJpeg/contents:0': image_data})

                    # Sort to show labels of first prediction in order of confidence
                    top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]
                    output = [[top_k[0],top_k[1],top_k[2],top_k[3],top_k[4]]]

                    answer = np.argmax(output)
                    per = max((np.array(output)).flatten())

                    if per<6 :
                        per+=3
                    value = []
                    value.append(answer)
                    value.append(per)
                    
                    return(value)
        sess.close()