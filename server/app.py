import pyrebase

config = {
    "apiKey": "AIzaSyAy1nWmjZ3-F5v_6ivgeG_bJaXhZrCtFM8",
    "authDomain": "test-a3da0.firebaseapp.com",
    "databaseURL": "https://test-a3da0.firebaseio.com",
    "projectId": "test-a3da0",
    "storageBucket": "test-a3da0.appspot.com",
    "messagingSenderId": "784675154021"
}

firebase = pyrebase.initialize_app(config)

db = firebase.database()

################ test ####################
#db.child("name").push({"name":"chang"})
#db.child("name").child("name").update({"name":"test"})
#users = db.child("name").child("name").get()
#print(users.key())
#db.child("name").remove()
#----------upload to db --------------
#db.child("filename is here").put("request file name is here") 
from flask import *
from gevent.pywsgi import WSGIServer
from flask_restful import Resource, Api
from werkzeug import secure_filename

app = Flask(__name__)
api = Api(app)
app.debug = True

@app.route('/upload',methods=['GET', 'POST'])
def upload_file():
    if request.files['file'] == 'POST':
        f = request.files['file']
        #저장할 경로 + 파일명
        f,save('/home/taeyeon/test/TeamProJect1/upload/')+secure_file(f.filename)
    
    return 'upload_file'
if __name__ == '__main__':
    app.run(host='0.0.0.0')
#http = WSGIServer(('0.0.0.0', 5000), app.wsgi_app)
#http.serve_forever()
