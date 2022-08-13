from flask import Flask

app = Flask(__name__)

@app.route('/')
def index():
  return 'Hi'
  
if __name__ == "main":
  app.run(host="127.0.0.1", port = 8080, debug = True)
