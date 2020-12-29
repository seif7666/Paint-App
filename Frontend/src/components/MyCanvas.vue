<template>
  <div id="app">
    <!-- @click="receive('/getShape')" -->

    <div id="menu">
      <div class="button">
        <ul>
          <li>
            <input
              type="radio"
              id="Line Segment"
              name="shape"
              value="Line Segment"
              @click="whichButton('non')"
            />
            <label for="Line Segment">Line Segment</label>
          </li>

          <li>
            <input
              type="radio"
              id="Circle"
              name="shape"
              value="Circle"
              @click="whichButton('non')"
            />
            <label for="Circle">Circle</label>
          </li>

          <li>
            <input
              type="radio"
              id="Ellipse"
              name="shape"
              value="Ellipse"
              @click="whichButton('non')"
            />
            <label for="Ellipse">Ellipse</label>
          </li>

          <li>
            <input
              type="radio"
              id="Triangle"
              name="shape"
              value="Triangle"
              @click="whichButton('non')"
            />
            <label for="Triangle">Triangle</label>
          </li>

          <li>
            <input
              type="radio"
              id="Rectangle"
              name="shape"
              value="Rectangle"
              @click="whichButton('non')"
            />
            <label for="Rectangle">Rectangle</label>
          </li>

          <li>
            <input
              type="radio"
              id="Square"
              name="shape"
              value="Square"
              @click="whichButton('non')"
            />
            <label for="Square">Square</label>
          </li>

          <li>
            <input
              type="radio"
              id="deletButton"
              name="shape"
              value="Delete"
              @click="whichButton('deletButton')"
            />
            <label for="deletButton">Delete</label>
          </li>

          <li>
            <input
              type="radio"
              id="moveButton"
              name="shape"
              value="Move"
              @click="whichButton('moveButton')"
            />

            <label for="moveButton">Move</label>
          </li>

          <li>
            <input
              type="radio"
              id="resizeButton"
              name="shape"
              value="Resize"
              @click="whichButton('resizeButton')"
            />
            <label for="resizeButton">Resize</label>
          </li>

          <li>
            <input
              type="radio"
              id="changeColorButton"
              name="shape"
              value="ChangeColor"
              @click="whichButton('changeColorButton')"
            />
            <label for="Square">Change colors</label>
          </li>
          <li>
            <input
              type="radio"
              id="copyButton"
              name="shape"
              value="copy"
              @click="whichButton('copyButton')"
            />
            <label for="copyButton">Copy</label>
          </li>
        </ul>
      </div>
    </div>


    <button class="bubbly-button" id="save" @mousedown="savePath">Save</button>
    <button class="bubbly-button" id="load" @mousedown="loadPath">Load</button>

    <button class="bubbly-button" type="button" @click="manageUndos('undo')">
      UNDO
    </button>
    <button class="bubbly-button" type="button" @click="manageUndos('redo')">
      REDO
    </button>
        <button class="bubbly-button" id="refresh" @mousedown="refresh">Refresh</button>


    <div class="color">
      <label for="pen-color" class="labelColor">Border Color : </label>
      <input type="color" id="pen-color" value="#000000" />
      <label for="fill-color" class="labelColor">&nbsp; fill color : </label>
      <input type="color" id="fill-color" value="#ffffff" /><br />
      <br />

      <!--     extra  -->

      <!--           extra  -->
    </div>

    <canvas
      id="myCanvas"
      width="1500"
      height="600"
      @mousedown="mianFunction"
      @mouseup="newPoints"
    ></canvas>
  </div>
</template>

<script>
import axios from "axios";
const BACKEND_URL = "http://127.0.0.1:8081";
export default {
  name: "Mycanvas",
  data() {
    return {
      canvas: null,
      x1: 0,
      y1: 0,
      x2: 0,
      y2: 0,
      x3: 0,
      y3: 0,
      i: 0,
      j: 0,
      w: 0,
      q: 0,
      endLineX: null,
      endLineY: null,
      isDrawing: false,
      selectedValue: null,
      dragging: false,
      dragStartLocation: null,
      snapshot: null,
      myCurrentButton: null,
      action: false,
      width: null,
      height: null,
      sideLength: null,
      rad: null,
      radX: null,
      radY: null,
      angle: null,
      oldX: null,
      oldY: null,
      newX: null,
      newY: null,
      secondx: null,
      secondy: null,
      newFill: null,
      newStroke: null,
      newSize: null,
      fileChooserPath: null,
      linex: 0,
      liney: 0,
    };
  },
  methods: {
    async refresh(){
      await this.toBeCalled();
    },
    async savePath() {
      ///Save must be done here
      let path = prompt("Enter place to be saved");
      let fileName = prompt("Enter file name");
      let temp = this.myCurrentButton;
      this.myCurrentButton = "save";
      let temp1 = this.newFill;
      let temp2 = this.newStroke;
      this.newFill = path;
      this.newStroke = fileName;
      this.sendOperation();
      // let XML_doc = await this.getDoc();
      // setTimeout(() => {
      //    if(XML_doc === null){
      //   alert("Problem in saving file");
      //   return
      // }
      // console.log(XML_doc);
      ///Now we save the document
      // this.openDialogueSave(XML_doc);
      this.myCurrentButton = temp;
      this.newFill = temp1;
      this.newStroke = temp2;
      // }, 500);
    },
    loadPath() {
      let path = prompt("Enter file path please: ");
      let temp = this.newFill;
      this.newFill = path;
      ///Put here path of file to be loaded
      this.myCurrentButton = "load";
      this.sendOperation();
      this.newFill = temp;
      this.toBeCalled();
    },
    newPoints(e) {
      this.newX = e.offsetX;
      this.newY = e.offsetY;
      console.log("new is  " + this.newX + "  " + this.newY);
    },
    whichButton(x) {
      if (x === "copyButton") {
        this.myCurrentButton = "copyButton";
        this.action = true;
      }
      if (x === "deletButton") {
        this.myCurrentButton = "deletButton";
        this.action = true;
      } else if (x === "moveButton") {
        this.myCurrentButton = "moveButton";
        this.action = true;
      } else if (x === "resizeButton") {
        this.myCurrentButton = "resizeButton";
        this.newSize = prompt("Enter new ratio ");
        this.action = true;
      } else if (x === "changeColorButton") {
        this.myCurrentButton = "changeColorButton";
        this.newFill = document.getElementById("fill-color").value;
        this.newStroke = document.getElementById("pen-color").value;
        this.action = true;
      } else if (x === "non") {
        this.action = false;
      }
    },
    showCoordinates(e) {
      this.x1 = e.offsetX;
      this.y1 = e.offsetY;
      console.log(this.x1 + this.y1);
    },
    checkSelected() {
      var allChoices = document.querySelectorAll('input[name="shape"]');
      for (const value of allChoices) {
        if (value.checked) {
          this.selectedValue = value.value;
          break;
        }
      }
    },
    async mianFunction(e) {
      this.x1 = e.offsetX;
      this.y1 = e.offsetY;
      console.log("x1  " + this.x1);
      console.log("y1  " + this.y1);
      this.checkSelected();
      this.whichButton();
      console.log("my action " + this.action);
      if (this.action === false) this.drawShape();
      else {
        console.log(this.myCurrentButton);
        ///////////////////////////////////////here actions of delete and resize and move and paint send to bcakend ///////////////
        if (
          this.myCurrentButton === "moveButton" ||
          this.myCurrentButton === "copyButton"
        ) {
          let tempx = this.x1;
          let tempy = this.y1;
          console.log(this.i);
          if (this.q == 0) {
            this.oldX = tempx;
            this.oldY = tempy;
          }
          console.log(this.q);
          if (this.q == 1) {
            this.newX = tempx;
            this.newY = tempy;
          }
          console.log("myOldx : " + this.oldX + " myoldy : " + this.oldY);
          console.log("mynewx : " + this.newX + "  this.newY  : " + this.newY);
          this.q = 1;
          if (
            this.oldX != null &&
            this.oldY != null &&
            this.newX != null &&
            this.newY != null
          ) {
            this.sendOperation();
            await this.toBeCalled();
            this.myCurrentButton = "";
            this.oldX = 0;
            this.oldY = 0;
            this.newX = 0;
            this.newY = 0;
            this.q = 0;
          }
        } else {
          this.sendOperation();
          await this.toBeCalled();
          this.myCurrentButton = "";
        }
      }
    },
    drawShape: function () {
      setTimeout(() => {
        ////////////////////////////////--------------------------------------------------
        console.log(`Selected value line 329 is ${this.selectedValue}`);
        this.draw();
        this.isDrawing = false;
      }, 200); ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    },
    draw: function () {
      this.canvas.strokeStyle = document.getElementById("pen-color").value;
      this.canvas.fillStyle = document.getElementById("fill-color").value;
      this.canvas.lineWidth = 5;
      switch (this.selectedValue) {
        case "Rectangle":
          this.width = prompt("Enter width");
          this.height = prompt("Enter height");
          this.sendShape("rectangle", this.height, this.width, 0);

          if (this.x1 === "null" && this.y1 === "null") {
            console.log("here rectangle");
          } else {
            //sendShape: function(name, len, wid, angle)
            this.canvas.beginPath();
            this.canvas.rect(this.x1, this.y1, this.width, this.height);
            this.canvas.stroke();
            this.canvas.fill();
            (this.x1 = null), (this.y1 = null);
          }
          break;
        case "Square":
          this.sideLength = prompt("Enter side Length");
          this.sendShape("square", this.sideLength, this.sideLength, 0);
          if (this.x1 === "null" && this.y1 === "null") {
            console.log("here square");
          } else {
            this.canvas.beginPath();
            this.canvas.rect(
              this.x1,
              this.y1,
              this.sideLength,
              this.sideLength
            );
            this.canvas.stroke();
            this.canvas.fill();
            (this.x1 = null), (this.y1 = null);
          }
          break;
        case "Line Segment":
          if (this.x1 === "null" && this.y1 === "null") {
            console.log("here line");
          } else {
            //drawFreeLine();
            let mynewx = null;
            let mynewY = null;
            // let myOldx = null;
            // let myOldY = null;
            let tempx = this.x1;
            let tempy = this.y1;
            console.log(this.i);
            if (this.w == 0) {
              this.linex = tempx;
              this.liney = tempy;
            }
            console.log(this.w);
            if (this.w == 1) {
              mynewx = tempx;
              mynewY = tempy;
            }
            console.log("linex : " + this.linex + " liney : " + this.liney);
            console.log("mynewx : " + mynewx + " mynewx : " + mynewY);
            this.w = 1;
            if (
              this.linex != null &&
              this.liney != null &&
              mynewx != null &&
              mynewY != null
            ) {
              this.sendPoints("Line Segment", [
                this.linex,
                this.liney,
                mynewx,
                mynewY,
              ]);
              this.canvas.beginPath();
              this.canvas.moveTo(this.linex, this.liney);
              this.canvas.lineTo(mynewx, mynewY);
              this.canvas.stroke();
              this.canvas.fill();
              this.canvas.closePath();
              this.linex = null;
              this.liney = null;
              mynewx = null;
              mynewY = null;
              this.w = 0;
            }
          }

          break;
        case "Triangle":
          if (this.x1 != null && this.y1 != null) {
            let thirdx = null;
            let thirdy = null;
            let tempx = this.x1;
            let tempy = this.y1;
            console.log("i1  " + this.w);
            console.log("j1  " + this.j);
            if (this.w == 0) {
              this.linex = tempx;
              this.liney = tempy;
              this.j++;
            }
            console.log("i2  " + this.w);
            console.log("j 2 " + this.j);

            if (this.w == 1 && this.j < 3) {
              this.secondx = tempx;
              this.secondy = tempy;
              this.j++;
            }
            console.log("i3  " + this.w);
            console.log("j3  " + this.j);
            if (this.j === 4) {
              thirdx = tempx;
              thirdy = tempy;
            }

            console.log("linex : " + this.linex + " liney : " + this.liney);
            console.log("2x : " + this.secondx + " 2y : " + this.secondy);
            console.log("3x : " + thirdx + " 3y : " + thirdy);
            console.log("i4  " + this.w);
            console.log("j4  " + this.j);
            this.w = 1;
            this.j++;
            if (
              this.linex != null &&
              this.liney != null &&
              this.secondx != null &&
              this.secondy != null &&
              thirdx != null &&
              thirdy != null
            ) {
              this.sendPoints("Triangle", [
                this.linex,
                this.liney,
                this.secondx,
                this.secondy,
                thirdx,
                thirdy,
              ]);
              this.canvas.beginPath();
              this.canvas.moveTo(this.linex, this.liney);
              this.canvas.lineTo(this.secondx, this.secondy);
              this.canvas.lineTo(thirdx, thirdy);
              this.canvas.lineTo(this.linex, this.liney);
              this.canvas.stroke();
              this.canvas.fill();
              this.canvas.closePath();
              this.linex = null;
              this.liney = null;
              this.secondx = null;
              this.secondy = null;
              thirdx = null;
              thirdy = null;
              this.w = 0;
              this.j = 0;
            }
          }

          break;
        case "Circle":
          this.rad = prompt("Enter radius");
          this.sendShape("circle", this.rad, this.rad, 0);
          if (this.x1 === "null" && this.y1 === "null") {
            console.log("here circle");
          } else {
            this.canvas.beginPath();
            this.canvas.arc(this.x1, this.y1, this.rad, 0, 2 * Math.PI);
            this.canvas.stroke();
            this.canvas.fill();
          }
          break;
        case "Ellipse":
          console.log("here eliepse 1");
          var raD = Math.PI / 180;
          console.log(raD);
          this.radX = prompt("Enter radius X");
          this.radY = prompt("Enter radius Y");
          this.sendShape("ellipse", this.radX, this.radY, 0);

          if (this.x1 === "null" && this.y1 === "null") {
            console.log("here");
          } else {
            this.canvas.beginPath();
            this.canvas.ellipse(
              this.x1,
              this.y1,
              this.radX,
              this.radY,
              0 * raD,
              0,
              360 * raD
            );
            console.log("here eliepse 2");
            this.canvas.stroke();
            this.canvas.fill();
            this.x1 = null;
            this.y1 = null;
          }
          break;

        default:
        // code block
      }
      this.selectedValue = null;
      this.action = false;
      this.x1 = null;
      this.y1 = null;
      this.x2 = null;
      this.x3 = null;
      this.y2 = null;
      this.y3 = null;
      this.i = 0;
      this.oldX = 0;
      this.oldY = 0;
      this.newX = null;
      this.newY = null;
      this.width = null;
      this.height = null;
      this.sideLength = null;
      this.rad = null;
      this.radx = null;
      this.radY = null;
    },
    manageUndos(op) {
      let temp = this.myCurrentButton;
      this.myCurrentButton = op;
      this.sendOperation();
      this.toBeCalled();
      this.myCurrentButton = temp;
    },
    reDraw(
      shapename,
      x,
      y,
      width,
      heigth,
      sideLength,
      rad,
      radX,
      radY,
      fillcolor,
      strokecolor
    ) {
      // width 2ndpointx  heigth 2ndpointy    sidelength 3rdpointx   rad 3rdpointy
      // this.canvas.clearRect(0, 0, 1500, 600); seif call it once//////--------------------------------------------------------------
      this.canvas.strokeStyle = strokecolor;
      this.canvas.fillStyle = fillcolor;
      this.canvas.lineWidth = 5;

      switch (shapename) {
        case "rectangle":
          this.canvas.beginPath();
          this.canvas.rect(x, y, width, heigth);
          this.canvas.stroke();
          this.canvas.fill();
          break;
        case "square":
          this.canvas.beginPath();
          this.canvas.rect(x, y, sideLength, sideLength);
          this.canvas.stroke();
          this.canvas.fill();
          break;
        case "line":
          this.canvas.beginPath();
          this.canvas.moveTo(x, y);
          this.canvas.lineTo(width, heigth);
          this.canvas.stroke();
          this.canvas.fill();
          this.canvas.closePath();
          break;
        case "triangle":
          this.canvas.beginPath();
          this.canvas.moveTo(x, y);
          this.canvas.lineTo(width, heigth);
          this.canvas.lineTo(sideLength, rad);
          this.canvas.lineTo(x, y);
          this.canvas.stroke();
          this.canvas.fill();
          this.canvas.closePath();
          break;
        case "circle":
          this.canvas.beginPath();
          this.canvas.arc(x, y, rad, 0, 2 * Math.PI);
          this.canvas.stroke();
          this.canvas.fill();
          break;
        case "ellipse":
          var raD = Math.PI / 180;
          this.canvas.beginPath();
          this.canvas.ellipse(x, y, radX, radY, 0 * raD, 0, 360 * raD);
          console.log("here eliepse 2");
          this.canvas.stroke();
          this.canvas.fill();
          break;
        default:
      }
      this.selectedValue = null;
    },
    send: function (url, pack) {
      url = BACKEND_URL + url;
      let request = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: pack,
      };
      fetch(url, request).then((resp) => resp.json);
      // .then((text) => console.log(text));
    },
    /**
     * Sent: will be:
     * Type of operation
     * point
     */
    sendOperation: function () {
      let pack = null;
      switch (this.myCurrentButton) {
        case "deletButton":
          pack = { operation: "Delete", x1: this.x1, y1: this.y1 };
          break;
        case "resizeButton":
          pack = {
            operation: "resize",
            x1: this.x1,
            y1: this.y1,
            ratio: this.newSize,
          };
          break;
        case "moveButton":
          // this.newPoints(this);
          console.log("Entered here");
          pack = {
            operation: "move",
            x1: this.oldX,
            y1: this.oldY,
            x2: this.newX,
            y2: this.newY,
          };
          break;
        case "changeColorButton":
          pack = {
            operation: "changeColorButton",
            x1: this.x1,
            y1: this.y1,
            fill: this.newFill,
            stroke: this.newStroke,
          };
          break;
        case "copyButton":
          // this.newPoints(this);
          console.log("Entered here");
          pack = {
            operation: "copy",
            x1: this.oldX,
            y1: this.oldY,
            x2: this.newX,
            y2: this.newY,
          };
          break;
        case "undo":
          console.log("UNDO here ");
          pack = {
            operation: "undo",
          };
          break;
        case "redo":
          console.log("REDO");
          pack = {
            operation: "redo",
          };
          break;
        case "save":
          pack = {
            operation: "save",
            fill: this.newFill,
            stroke: this.newStroke,
          };
          break;
        case "load":
          pack = {
            operation: "load",
            fill: this.newFill,
          };
          break;
      }
      pack = JSON.stringify(pack);
      // ///Now we send.

      this.send("/operation", pack);
    },
    sendPoints: function (name, points) {
      let pack = null;
      if (name === "Line Segment") {
        //Points x1,y1,x2,y2
        pack = {
          nameOfShape: "line",
          x: points[0],
          y: points[1],
          x2: points[2],
          y2: points[3],
          fill: this.canvas.fillStyle,
          stroke: this.canvas.strokeStyle,
        };
      } else if (name === "Triangle") {
        pack = {
          nameOfShape: "triangle",
          x: points[0],
          y: points[1],
          x2: points[2],
          y2: points[3],
          x3: points[4],
          y3: points[5],
          fill: this.canvas.fillStyle,
          stroke: this.canvas.strokeStyle,
        };
      }
      pack = JSON.stringify(pack);
      console.log("In send points " + pack);
      this.send("/draw", pack);
    },
    sendShape: function (name, len, wid, angle) {
      let pack = {
        nameOfShape: name,
        x: this.x1,
        y: this.y1,
        length: len,
        width: wid,
        angle: angle,
        fill: this.canvas.fillStyle,
        stroke: this.canvas.strokeStyle,
      };
      pack = JSON.stringify(pack);
      console.log("In send shape " + pack);
      ///Now we send.
      // console.log("JsOn before send is ");
      // console.log(pack);
      this.send("/draw", pack);
    },
    async toBeCalled() {
      console.log("Entered in line 1106 (To Be Called!!!");
      this.canvas.clearRect(0, 0, 1500, 600);
      const url = BACKEND_URL + "/getShapes";
      console.log(url);
      let shapes = null;
      // eslint-disable-next-line no-constant-condition
      while (true) {
        axios.get(url).then((response) => (shapes = response.data));
        axios.get(url).then((response) => (shapes = response.data));
        if (shapes === null) break;
      }

      setTimeout(() => {
        console.log(shapes);
        if (shapes === null) {
          console.log("Shapes is null!!!");
          return;
        }
        //Now we begin looping
        for (let i = 0; i < shapes.length; i++) {
          ///Handle in triangle and line segment
          let shape = shapes[i];
          if (shape === null) continue;
          console.log(shape);
          if (shape.nameOfShape === "line") {
            this.reDraw(
              shape.nameOfShape,
              shape.x,
              shape.y,
              shape.x2,
              shape.y2,
              shape.x3,
              shape.y3,
              shape.length,
              shape.width,
              shape.fill,
              shape.stroke
            );
          } else if (shape.nameOfShape == "triangle") {
            this.reDraw(
              shape.nameOfShape,
              shape.x,
              shape.y,
              shape.x2,
              shape.y2,
              shape.x3,
              shape.y3,
              shape.length,
              shape.width,
              shape.fill,
              shape.stroke
            );
          } else {
            ///Other shapes
            this.reDraw(
              shape.nameOfShape,
              shape.x,
              shape.y,
              shape.width,
              shape.length,
              shape.length,
              shape.length,
              shape.length,
              shape.width,
              shape.fill,
              shape.stroke
            );
          }
        }
        this.selectedValue = null;
        this.action = false;
        this.x1 = null;
        this.y1 = null;
        this.x2 = null;
        this.x3 = null;
        this.y2 = null;
        this.y3 = null;
        this.newX = null;
        this.newY = null;
        this.width = null;
        this.height = null;
        this.sideLength = null;
        this.rad = null;
        this.radx = null;
        this.radY = null;
      }, 200); ///////////-----------------------------------------------------------------------------------
      ///Now we want to receive all shapes to be drawn
      // width 2ndpointx  heigth 2ndpointy    sidelength 3rdpointx   rad 3rdpointy
      console.log("Finished to be called");
    },

    //////////---------------clear();
    //////////--------redraw();
    getDoc: async function () {
      //Here we will receive a text file
      let url = BACKEND_URL + "/saved";
      console.log(url);
      let doc = null;
      try {
        await axios.get(url).then((res) => (doc = JSON.stringify(res.data)));
      } catch (error) {
        console.log("ERROR in save");
      }
      return doc;
    },
  }, //methods
  mounted() {
    var c = document.getElementById("myCanvas");
    this.canvas = c.getContext("2d");
    this.canvas.height = window.innerHeight;
    this.canvas.width = window.innerWidth;
    axios.get(URL + "/getShapes");
  },
};
</script>

<style scoped>
/* herrrrrrrrrrrrrrrrrrrrrrrrrrrrrrreeeeeeeeeeeeeeeeeeeeeeeeee*/
ul {
  list-style: none;
  display: flex;
  flex-direction: row;
}

.button li {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 24px;
}

.button li:hover {
  background-color: #ddd;
  color: black;
}
.button li:hover {
  background-color: #4caf50;
  color: white;
}
.button {
  background-color: #333;
  overflow: hidden;
}
#myCanvas {
  border: 5px solid grey;
  background-color: #ffffff;
}

#menu {
  display: flex;
}
#others {
  margin: 3em;
  display: flex;
  flex-direction: row;
}
.button {
  margin: auto;
  display: flexbox;
  justify-content: left;
}

button {
  margin: 3em;
}

.bubbly-button {
  font-family: "Helvetica", "Arial", sans-serif;
  display: inline-block;
  font-size: 1em;
  padding: 1em 2em;
  margin-top: 100px;
  margin-bottom: 60px;
  -webkit-appearance: none;
  appearance: none;
  background-color: #17421d;
  color: #fff;
  border-radius: 4px;
  border: none;
  cursor: pointer;
  position: relative;
  transition: transform ease-in 0.1s, box-shadow ease-in 0.25s;
  box-shadow: 0 2px 25px rgba(144, 236, 149, 0.5);
}
.bubbly-button:focus {
  outline: 0;
}
.bubbly-button:before,
.bubbly-button:after {
  position: absolute;
  content: "";
  display: block;
  width: 140%;
  height: 100%;
  left: -20%;
  z-index: -1000;
  transition: all ease-in-out 0.5s;
  background-repeat: no-repeat;
}
.bubbly-button:before {
  display: none;
  top: -75%;
  background-image: radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, transparent 20%, #ff0081 20%, transparent 30%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, transparent 10%, #ff0081 15%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%);
  background-size: 10% 10%, 20% 20%, 15% 15%, 20% 20%, 18% 18%, 10% 10%, 15% 15%,
    10% 10%, 18% 18%;
}
.bubbly-button:after {
  display: none;
  bottom: -75%;
  background-image: radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, transparent 10%, #ff0081 15%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%),
    radial-gradient(circle, #ff0081 20%, transparent 20%);
  background-size: 15% 15%, 20% 20%, 18% 18%, 20% 20%, 15% 15%, 10% 10%, 20% 20%;
}
.bubbly-button:active {
  transform: scale(0.9);
  background-color: #e60074;
  box-shadow: 0 2px 25px rgba(255, 0, 130, 0.2);
}
.bubbly-button.animate:before {
  display: block;
  animation: topBubbles ease-in-out 0.75s forwards;
}
.bubbly-button.animate:after {
  display: block;
  animation: bottomBubbles ease-in-out 0.75s forwards;
}

@keyframes topBubbles {
  0% {
    background-position: 5% 90%, 10% 90%, 10% 90%, 15% 90%, 25% 90%, 25% 90%,
      40% 90%, 55% 90%, 70% 90%;
  }
  50% {
    background-position: 0% 80%, 0% 20%, 10% 40%, 20% 0%, 30% 30%, 22% 50%,
      50% 50%, 65% 20%, 90% 30%;
  }
  100% {
    background-position: 0% 70%, 0% 10%, 10% 30%, 20% -10%, 30% 20%, 22% 40%,
      50% 40%, 65% 10%, 90% 20%;
    background-size: 0% 0%, 0% 0%, 0% 0%, 0% 0%, 0% 0%, 0% 0%;
  }
}
@keyframes bottomBubbles {
  0% {
    background-position: 10% -10%, 30% 10%, 55% -10%, 70% -10%, 85% -10%,
      70% -10%, 70% 0%;
  }
  50% {
    background-position: 0% 80%, 20% 80%, 45% 60%, 60% 100%, 75% 70%, 95% 60%,
      105% 0%;
  }
  100% {
    background-position: 0% 90%, 20% 90%, 45% 70%, 60% 110%, 75% 80%, 95% 70%,
      110% 10%;
    background-size: 0% 0%, 0% 0%, 0% 0%, 0% 0%, 0% 0%, 0% 0%;
  }
}

input[type="file"] {
  display: inline-block;
  padding: 12px 18px;
  cursor: pointer;
  border-radius: 5px;
  background-color: #8ebf42;
}
input[type="color"] {
  -webkit-appearance: none;
  border: none;
  width: 32px;
  height: 32px;
}
input[type="color"]::-webkit-color-swatch-wrapper {
  padding: 0;
}
input[type="color"]::-webkit-color-swatch {
  border: none;
}

.labelColor {
  color: #000000;
  font-weight: bold;
  width: 150px;
  font-size: 22px;
}
</style>