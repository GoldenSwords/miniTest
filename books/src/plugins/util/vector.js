class Vector {
  constructor () {
    const CAV = {
      FRONT: 0,
      BACK: 1,
      DOUBLE: 2,
      SVGNS: 'http://www.w3.org/2000/svg'
    }
    CAV.Array = typeof Float32Array === 'function' ? Float32Array : Array
    CAV.Utils = {
      isNumber: function (a) {
        return !isNaN(parseFloat(a)) && isFinite(a)
      }
    }
    Math.PIM2 = Math.PI * 2
    Math.PID2 = Math.PI / 2
    Math.randomInRange = function (a, b) {
      return a + (b - a) * Math.random()
    }
    Math.clamp = function (a, b, c) {
      a = Math.max(a, b)
      a = Math.min(a, c)
      return a
    }
    CAV.Vector3 = {
      create: function (a, b, c) {
        var d = new CAV.Array(3)
        this.set(d, a, b, c)
        return d
      },
      clone: function (a) {
        var b = this.create()
        this.copy(b, a)
        return b
      },
      set: function (a, b, c, d) {
        a[0] = b || 0
        a[1] = c || 0
        a[2] = d || 0
        return this
      },
      setX: function (a, b) {
        a[0] = b || 0
        return this
      },
      setY: function (a, b) {
        a[1] = b || 0
        return this
      },
      setZ: function (a, b) {
        a[2] = b || 0
        return this
      },
      copy: function (a, b) {
        a[0] = b[0]
        a[1] = b[1]
        a[2] = b[2]
        return this
      },
      add: function (a, b) {
        a[0] += b[0]
        a[1] += b[1]
        a[2] += b[2]
        return this
      },
      addVectors: function (a, b, c) {
        a[0] = b[0] + c[0]
        a[1] = b[1] + c[1]
        a[2] = b[2] + c[2]
        return this
      },
      addScalar: function (a, b) {
        a[0] += b
        a[1] += b
        a[2] += b
        return this
      },
      subtract: function (a, b) {
        a[0] -= b[0]
        a[1] -= b[1]
        a[2] -= b[2]
        return this
      },
      subtractVectors: function (a, b, c) {
        a[0] = b[0] - c[0]
        a[1] = b[1] - c[1]
        a[2] = b[2] - c[2]
        return this
      },
      subtractScalar: function (a, b) {
        a[0] -= b
        a[1] -= b
        a[2] -= b
        return this
      },
      multiply: function (a, b) {
        a[0] *= b[0]
        a[1] *= b[1]
        a[2] *= b[2]
        return this
      },
      multiplyVectors: function (a, b, c) {
        a[0] = b[0] * c[0]
        a[1] = b[1] * c[1]
        a[2] = b[2] * c[2]
        return this
      },
      multiplyScalar: function (a, b) {
        a[0] *= b
        a[1] *= b
        a[2] *= b
        return this
      },
      divide: function (a, b) {
        a[0] /= b[0]
        a[1] /= b[1]
        a[2] /= b[2]
        return this
      },
      divideVectors: function (a, b, c) {
        a[0] = b[0] / c[0]
        a[1] = b[1] / c[1]
        a[2] = b[2] / c[2]
        return this
      },
      divideScalar: function (a, b) {
        if (b !== 0) {
          a[0] /= b
          a[1] /= b
          a[2] /= b
        } else {
          a[0] = 0
          a[1] = 0
          a[2] = 0
        }
        return this
      },
      cross: function (a, b) {
        var c = a[0]
        var d = a[1]
        var e = a[2]
        a[0] = d * b[2] - e * b[1]
        a[1] = e * b[0] - c * b[2]
        a[2] = c * b[1] - d * b[0]
        return this
      },
      crossVectors: function (a, b, c) {
        a[0] = b[1] * c[2] - b[2] * c[1]
        a[1] = b[2] * c[0] - b[0] * c[2]
        a[2] = b[0] * c[1] - b[1] * c[0]
        return this
      },
      min: function (a, b) {
        a[0] < b && (a[0] = b)
        a[1] < b && (a[1] = b)
        a[2] < b && (a[2] = b)
        return this
      },
      max: function (a, b) {
        a[0] > b && (a[0] = b)
        a[1] > b && (a[1] = b)
        a[2] > b && (a[2] = b)
        return this
      },
      clamp: function (a, b, c) {
        this.min(a, b)
        this.max(a, c)
        return this
      },
      limit: function (a, b, c) {
        var d = this.length(a)
        b !== null && d < b ? this.setLength(a, b) : c !== null && d > c && this.setLength(a, c)
        return this
      },
      dot: function (a, b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2]
      },
      normalise: function (a) {
        return this.divideScalar(a, this.length(a))
      },
      negate: function (a) {
        return this.multiplyScalar(a, -1)
      },
      distanceSquared: function (a, b) {
        var c = a[0] - b[0]
        var d = a[1] - b[1]
        var e = a[2] - b[2]
        return c * c + d * d + e * e
      },
      distance: function (a, b) {
        return Math.sqrt(this.distanceSquared(a, b))
      },
      lengthSquared: function (a) {
        return a[0] * a[0] + a[1] * a[1] + a[2] * a[2]
      },
      length: function (a) {
        return Math.sqrt(this.lengthSquared(a))
      },
      setLength: function (a, b) {
        var c = this.length(a)
        c !== 0 && b !== c && this.multiplyScalar(a, b / c)
        return this
      }
    }
    CAV.Vector4 = {
      create: function (a, b, c) {
        var d = new CAV.Array(4)
        this.set(d, a, b, c)
        return d
      },
      set: function (a, b, c, d, e) {
        a[0] = b || 0
        a[1] = c || 0
        a[2] = d || 0
        a[3] = e || 0
        return this
      },
      setX: function (a, b) {
        a[0] = b || 0
        return this
      },
      setY: function (a, b) {
        a[1] = b || 0
        return this
      },
      setZ: function (a, b) {
        a[2] = b || 0
        return this
      },
      setW: function (a, b) {
        a[3] = b || 0
        return this
      },
      add: function (a, b) {
        a[0] += b[0]
        a[1] += b[1]
        a[2] += b[2]
        a[3] += b[3]
        return this
      },
      multiplyVectors: function (a, b, c) {
        a[0] = b[0] * c[0]
        a[1] = b[1] * c[1]
        a[2] = b[2] * c[2]
        a[3] = b[3] * c[3]
        return this
      },
      multiplyScalar: function (a, b) {
        a[0] *= b
        a[1] *= b
        a[2] *= b
        a[3] *= b
        return this
      },
      min: function (a, b) {
        a[0] < b && (a[0] = b)
        a[1] < b && (a[1] = b)
        a[2] < b && (a[2] = b)
        a[3] < b && (a[3] = b)
        return this
      },
      max: function (a, b) {
        a[0] > b && (a[0] = b)
        a[1] > b && (a[1] = b)
        a[2] > b && (a[2] = b)
        a[3] > b && (a[3] = b)
        return this
      },
      clamp: function (a, b, c) {
        this.min(a, b)
        this.max(a, c)
        return this
      }
    }
    CAV.Color = function (a, b) {
      this.rgba = CAV.Vector4.create()
      this.hex = a || '#000000'
      this.opacity = CAV.Utils.isNumber(b) ? b : 1
      this.set(this.hex, this.opacity)
    }
    CAV.Color.prototype = {
      set: function (a, b) {
        a = a.replace('#', '')
        var c = a.length / 3
        this.rgba[0] = parseInt(a.substring(c * 0, c * 1), 16) / 255
        this.rgba[1] = parseInt(a.substring(c * 1, c * 2), 16) / 255
        this.rgba[2] = parseInt(a.substring(c * 2, c * 3), 16) / 255
        this.rgba[3] = CAV.Utils.isNumber(b) ? b : this.rgba[3]
        return this
      },
      hexify: function (a) {
        a = Math.ceil(a * 255).toString(16)
        a.length === 1 && (a = '0' + a)
        return a
      },
      format: function () {
        var a = this.hexify(this.rgba[0])
        var b = this.hexify(this.rgba[1])
        var c = this.hexify(this.rgba[2])
        this.hex = '#' + a + b + c
        return this.hex
      }
    }
    CAV.Object = function () {
      this.position = CAV.Vector3.create()
    }
    CAV.Object.prototype = {
      setPosition: function (a, b, c) {
        CAV.Vector3.set(this.position, a, b, c)
        return this
      }
    }
    CAV.Light = function (a, b) {
      CAV.Object.call(this)
      this.ambient = new CAV.Color(a || '#FFFFFF')
      this.diffuse = new CAV.Color(b || '#FFFFFF')
      this.ray = CAV.Vector3.create()
    }
    CAV.Light.prototype = Object.create(CAV.Object.prototype)
    CAV.Vertex = function (a, b, c) {
      this.position = CAV.Vector3.create(a, b, c)
    }
    CAV.Vertex.prototype = {
      setPosition: function (a, b, c) {
        CAV.Vector3.set(this.position, a, b, c)
        return this
      }
    }
    CAV.Triangle = function (a, b, c) {
      this.a = a || new CAV.Vertex()
      this.b = b || new CAV.Vertex()
      this.c = c || new CAV.Vertex()
      this.vertices = [this.a, this.b, this.c]
      this.u = CAV.Vector3.create()
      this.v = CAV.Vector3.create()
      this.centroid = CAV.Vector3.create()
      this.normal = CAV.Vector3.create()
      this.color = new CAV.Color()
      this.polygon = document.createElementNS(CAV.SVGNS, 'polygon')
      this.polygon.setAttributeNS(null, 'stroke-linejoin', 'round')
      this.polygon.setAttributeNS(null, 'stroke-miterlimit', '1')
      this.polygon.setAttributeNS(null, 'stroke-width', '1')
      this.computeCentroid()
      this.computeNormal()
    }
    CAV.Triangle.prototype = {
      computeCentroid: function () {
        this.centroid[0] = this.a.position[0] + this.b.position[0] + this.c.position[0]
        this.centroid[1] = this.a.position[1] + this.b.position[1] + this.c.position[1]
        this.centroid[2] = this.a.position[2] + this.b.position[2] + this.c.position[2]
        CAV.Vector3.divideScalar(this.centroid, 3)
        return this
      },
      computeNormal: function () {
        CAV.Vector3.subtractVectors(this.u, this.b.position, this.a.position)
        CAV.Vector3.subtractVectors(this.v, this.c.position, this.a.position)
        CAV.Vector3.crossVectors(this.normal, this.u, this.v)
        CAV.Vector3.normalise(this.normal)
        return this
      }
    }
    CAV.Geometry = function () {
      this.vertices = []
      this.triangles = []
      this.dirty = false
    }
    CAV.Geometry.prototype = {
      update: function () {
        if (this.dirty) {
          var a, b
          for (a = this.triangles.length - 1; a >= 0; a--) {
            b = this.triangles[a]
            b.computeCentroid()
            b.computeNormal()
            this.dirty = false
          }
        }
        return this
      }
    }
    CAV.Plane = function (a, b, c, d) {
      CAV.Geometry.call(this)
      this.width = a || 100
      this.height = b || 100
      this.segments = c || 4
      this.slices = d || 4
      this.segmentWidth = this.width / this.segments
      this.sliceHeight = this.height / this.slices
      var e, f, g
      c = []
      e = this.width * -0.5
      f = this.height * 0.5
      for (a = 0; a <= this.segments; a++) {
        c.push([])
        for (b = 0; b <= this.slices; b++) {
          d = new CAV.Vertex(e + a * this.segmentWidth, f - b * this.sliceHeight)
          c[a].push(d)
          this.vertices.push(d)
        }
      }
      for (a = 0; a < this.segments; a++) {
        for (b = 0; b < this.slices; b++) {
          d = c[a + 0][b + 0]
          e = c[a + 0][b + 1]
          f = c[a + 1][b + 0]
          g = c[a + 1][b + 1]
          const t0 = new CAV.Triangle(d, e, f)
          const t1 = new CAV.Triangle(f, e, g)
          this.triangles.push(t0, t1)
        }
      }
    }
    CAV.Plane.prototype = Object.create(CAV.Geometry.prototype)
    CAV.Material = function (a, b) {
      this.ambient = new CAV.Color(a || '#444444')
      this.diffuse = new CAV.Color(b || '#FFFFFF')
      this.slave = new CAV.Color()
    }
    CAV.Mesh = function (a, b) {
      CAV.Object.call(this)
      this.geometry = a || new CAV.Geometry()
      this.material = b || new CAV.Material()
      this.side = CAV.FRONT
      this.visible = true
    }
    CAV.Mesh.prototype = Object.create(CAV.Object.prototype)
    CAV.Mesh.prototype.update = function (a, b) {
      var c, d, e, f, g
      this.geometry.update()
      if (b) {
        for (c = this.geometry.triangles.length - 1; c >= 0; c--) {
          d = this.geometry.triangles[c]
          CAV.Vector4.set(d.color.rgba)
          for (e = a.length - 1; e >= 0; e--) {
            f = a[e]
            CAV.Vector3.subtractVectors(f.ray, f.position, d.centroid)
            CAV.Vector3.normalise(f.ray)
            g = CAV.Vector3.dot(d.normal, f.ray)
            this.side === CAV.FRONT ? g = Math.max(g, 0) : this.side === CAV.BACK ? g = Math.abs(Math.min(g, 0)) : this.side === CAV.DOUBLE && (g = Math.max(Math.abs(g), 0))
            CAV.Vector4.multiplyVectors(this.material.slave.rgba, this.material.ambient.rgba, f.ambient.rgba)
            CAV.Vector4.add(d.color.rgba, this.material.slave.rgba)
            CAV.Vector4.multiplyVectors(this.material.slave.rgba, this.material.diffuse.rgba, f.diffuse.rgba)
            CAV.Vector4.multiplyScalar(this.material.slave.rgba, g)
            CAV.Vector4.add(d.color.rgba, this.material.slave.rgba)
            CAV.Vector4.clamp(d.color.rgba, 0, 1)
          }
        }
      }
      return this
    }
    CAV.Scene = function () {
      this.meshes = []
      this.lights = []
    }
    CAV.Scene.prototype = {
      add: function (a) {
        a instanceof CAV.Mesh && !~this.meshes.indexOf(a) ? this.meshes.push(a) : a instanceof CAV.Light && !~this.lights.indexOf(a) && this.lights.push(a)
        return this
      },
      remove: function (a) {
        a instanceof CAV.Mesh && ~this.meshes.indexOf(a) ? this.meshes.splice(this.meshes.indexOf(a), 1) : a instanceof CAV.Light && ~this.lights.indexOf(a) && this.lights.splice(this.lights.indexOf(a), 1)
        return this
      }
    }
    CAV.Renderer = function () {
      this.halfHeight = this.halfWidth = this.height = this.width = 0
    }
    CAV.Renderer.prototype = {
      setSize: function (a, b) {
        if (!(this.width === a && this.height === b)) {
          this.width = a
          this.height = b
          this.halfWidth = this.width * 0.5
          this.halfHeight = this.height * 0.5
          return this
        }
      },
      clear: function () {
        return this
      },
      render: function () {
        return this
      }
    }
    CAV.CanvasRenderer = function () {
      CAV.Renderer.call(this)
      this.element = document.createElement('canvas')
      this.element.style.display = 'block'
      this.context = this.element.getContext('2d')
      this.setSize(this.element.width, this.element.height)
    }
    CAV.CanvasRenderer.prototype = Object.create(CAV.Renderer.prototype)
    CAV.CanvasRenderer.prototype.setSize = function (a, b) {
      CAV.Renderer.prototype.setSize.call(this, a, b)
      this.element.width = a
      this.element.height = b
      this.context.setTransform(1, 0, 0, -1, this.halfWidth, this.halfHeight)
      return this
    }
    CAV.CanvasRenderer.prototype.clear = function () {
      CAV.Renderer.prototype.clear.call(this)
      this.context.clearRect(-this.halfWidth, -this.halfHeight, this.width, this.height)
      return this
    }
    CAV.CanvasRenderer.prototype.render = function (a) {
      CAV.Renderer.prototype.render.call(this, a)
      var b, c, d, e, f
      this.clear()
      this.context.lineJoin = 'round'
      this.context.lineWidth = 1
      for (b = a.meshes.length - 1; b >= 0; b--) {
        c = a.meshes[b]
        if (c.visible) {
          c.update(a.lights, true)
          for (d = c.geometry.triangles.length - 1; d >= 0; d--) {
            e = c.geometry.triangles[d]
            f = e.color.format()
            this.context.beginPath()
            this.context.moveTo(e.a.position[0], e.a.position[1])
            this.context.lineTo(e.b.position[0], e.b.position[1])
            this.context.lineTo(e.c.position[0], e.c.position[1])
            this.context.closePath()
            this.context.strokeStyle = f
            this.context.fillStyle = f
            this.context.stroke()
            this.context.fill()
          }
        }
      }
      return this
    }
    this.CAV = CAV
  }
  Victor (container, anitOut) {
    this.timer = null
    if (document.createElement('canvas').getContext) {
      this.t = {
        width: 1.5,
        height: 1.5,
        depth: 10,
        segments: 12,
        slices: 6,
        xRange: 0.8,
        yRange: 0.1,
        zRange: 1,
        ambient: '#525252',
        diffuse: '#A5A5A5',
        speed: 0.0002
      }
      this.G = {
        count: 2,
        xyScalar: 1,
        zOffset: 100,
        ambient: '#1c8bff',
        diffuse: '#1c8bff',
        speed: 0.001,
        gravity: 1200,
        dampening: 0.95,
        minLimit: 10,
        maxLimit: null,
        minDistance: 20,
        maxDistance: 400,
        autopilot: false,
        draw: false,
        bounds: this.CAV.Vector3.create(),
        step: this.CAV.Vector3.create(Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1))
      }
      this.m = 'canvas'
      this.E = 'svg'
      this.x = {
        renderer: this.m
      }
      this.i = undefined
      this.n = Date.now()
      this.L = this.CAV.Vector3.create()
      this.k = this.CAV.Vector3.create()
      this.z = document.getElementById(container || 'container')
      this.w = document.getElementById(anitOut || 'anitOut')
      this.D = undefined
      this.I = undefined
      this.h = undefined
      this.q = undefined
      this.y = undefined
      this.g = undefined
      this.r = undefined
      this.C()
    }
    return this.J
  }
  destory () {
    if (this.timer) {
      clearTimeout(this.timer)
    }
  }
  C () {
    this.F()
    this.p()
    this.s()
    this.B()
    this.v()
    this.K(this.z.offsetWidth, this.z.offsetHeight)
    this.o()
  }
  F () {
    this.g = new this.CAV.CanvasRenderer()
    this.H(this.x.renderer)
  }
  H (N) {
    if (this.D) {
      this.w.removeChild(this.D.element)
    }
    switch (N) {
      case this.m:
        this.D = this.g
        break
    }
    this.D.setSize(this.z.offsetWidth, this.z.offsetHeight)
    this.w.appendChild(this.D.element)
  }
  p () {
    this.I = new this.CAV.Scene()
  }
  s () {
    this.I.remove(this.h)
    this.D.clear()
    this.q = new this.CAV.Plane(this.t.width * this.D.width, this.t.height * this.D.height, this.t.segments, this.t.slices)
    this.y = new this.CAV.Material(this.t.ambient, this.t.diffuse)
    this.h = new this.CAV.Mesh(this.q, this.y)
    this.I.add(this.h)
    var N, O
    for (N = this.q.vertices.length - 1; N >= 0; N--) {
      O = this.q.vertices[N]
      O.anchor = this.CAV.Vector3.clone(O.position)
      O.step = this.CAV.Vector3.create(Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1))
      O.time = Math.randomInRange(0, Math.PIM2)
    }
  }
  B () {
    var O, N
    for (O = this.I.lights.length - 1; O >= 0; O--) {
      N = this.I.lights[O]
      this.I.remove(N)
    }
    this.D.clear()
    for (O = 0; O < this.G.count; O++) {
      N = new this.CAV.Light(this.G.ambient, this.G.diffuse)
      N.ambientHex = N.ambient.format()
      N.diffuseHex = N.diffuse.format()
      this.I.add(N)
      N.mass = Math.randomInRange(0.5, 1)
      N.velocity = this.CAV.Vector3.create()
      N.acceleration = this.CAV.Vector3.create()
      N.force = this.CAV.Vector3.create()
    }
  }
  K (O, N) {
    this.D.setSize(O, N)
    this.CAV.Vector3.set(this.L, this.D.halfWidth, this.D.halfHeight)
    this.s()
  }
  o () {
    this.i = Date.now() - this.n
    this.u()
    this.M()
    this.timer = setTimeout(() => {
      this.o()
    }, 10)
    // requestAnimationFrame(this.o); // this.o
  }
  u () {
    var Q, P, O, R, T, V, U
    var S = this.t.depth / 2
    this.CAV.Vector3.copy(this.G.bounds, this.L)
    this.CAV.Vector3.multiplyScalar(this.G.bounds, this.G.xyScalar)
    this.CAV.Vector3.setZ(this.k, this.G.zOffset)
    for (R = this.I.lights.length - 1; R >= 0; R--) {
      T = this.I.lights[R]
      this.CAV.Vector3.setZ(T.position, this.G.zOffset)
      var N = Math.clamp(this.CAV.Vector3.distanceSquared(T.position, this.k), this.G.minDistance, this.G.maxDistance)
      var W = this.G.gravity * T.mass / N
      this.CAV.Vector3.subtractVectors(T.force, this.k, T.position)
      this.CAV.Vector3.normalise(T.force)
      this.CAV.Vector3.multiplyScalar(T.force, W)
      this.CAV.Vector3.set(T.acceleration)
      this.CAV.Vector3.add(T.acceleration, T.force)
      this.CAV.Vector3.add(T.velocity, T.acceleration)
      this.CAV.Vector3.multiplyScalar(T.velocity, this.G.dampening)
      this.CAV.Vector3.limit(T.velocity, this.G.minLimit, this.G.maxLimit)
      this.CAV.Vector3.add(T.position, T.velocity)
    }
    for (V = this.q.vertices.length - 1; V >= 0; V--) {
      U = this.q.vertices[V]
      Q = Math.sin(U.time + U.step[0] * this.i * this.t.speed)
      P = Math.cos(U.time + U.step[1] * this.i * this.t.speed)
      O = Math.sin(U.time + U.step[2] * this.i * this.t.speed)
      this.CAV.Vector3.set(U.position, this.t.xRange * this.q.segmentWidth * Q, this.t.yRange * this.q.sliceHeight * P, this.t.zRange * S * O - S)
      this.CAV.Vector3.add(U.position, U.anchor)
    }
    this.q.dirty = true
  }
  M () {
    this.D.render(this.I)
  }
  J (O) {
    var Q, N
    var S = O
    this.P = function (T) {
      for (Q = 0, this.l = this.I.lights.length; Q < this.l; Q++) {
        N = this.I.lights[Q]
        N.ambient.set(T)
        N.ambientHex = N.ambient.format()
      }
    }
    this.R = function (T) {
      for (Q = 0, this.l = this.I.lights.length; Q < this.l; Q++) {
        N = this.I.lights[Q]
        N.diffuse.set(T)
        N.diffuseHex = N.diffuse.format()
      }
    }
    return {
      set: function () {
        this.P(S[0])
        this.R(S[1])
      }
    }
  }
  v () {
    window.addEventListener('resize', this.j)
  }
  A (N) {
    this.CAV.Vector3.set(this.k, N.x, this.D.height - N.y)
    this.CAV.Vector3.subtract(this.k, this.L)
  }
  j (N) {
    this.K(this.z.offsetWidth, this.z.offsetHeight)
    this.M()
  }
}
Vector.install = function () {
  return new Vector()
}

export default Vector
